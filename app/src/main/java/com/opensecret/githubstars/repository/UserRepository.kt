package com.opensecret.githubstars.repository

import android.util.Log
import com.opensecret.githubstars.dao.UserDao
import com.opensecret.githubstars.data.BaseData
import com.opensecret.githubstars.data.HeaderData
import com.opensecret.githubstars.data.UserData
import com.opensecret.githubstars.model.Search
import com.opensecret.githubstars.model.User
import com.opensecret.githubstars.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class UserRepository @Inject constructor(var apiService: ApiService, var userDao: UserDao) {

    val TAG = javaClass.simpleName

    suspend fun searchRemoteUser(query: String): List<BaseData> {
        val remoteList: ArrayList<BaseData> = ArrayList()
        apiService.getSearchUsers(query).enqueue(object : Callback<Search> {
            override fun onResponse(
                call: Call<Search>,
                response: Response<Search>
            ) {
                if (!response.isSuccessful) return

                val searchResponse = response.body()!!
                for (user in searchResponse.items.sortedBy { it.login }) {
                    val index = getInitialIndex(user.login[0])
                    val header = HeaderData().also { it.index = index }
                    if (remoteList.find { it.index == index } == null) remoteList.add(header)

                    remoteList.add(UserData().also {
                        it.index = index
                        it.user = user
                    })
                }
            }

            override fun onFailure(call: Call<Search>, t: Throwable) {
                Log.d(TAG, "Retrofit failure - $t")
            }
        })

        return remoteList
    }

    suspend fun searchLocalUser(query: String): List<BaseData> {
        val localList: ArrayList<BaseData> = ArrayList()
        val userList: List<User> =
            if (query.isNullOrEmpty().not()) {
                userDao.getUserByQuery("%$query%")
            } else {
                userDao.getAll()
            } ?: emptyList()

        for (user in userList.sortedBy { it.login }) {
            val index = getInitialIndex(user.login[0])
            val header = HeaderData().also { it.index = index }
            if (localList.find{ it.index == index } == null) localList.add(header)

            localList.add(UserData().also {
                it.index = index
                it.user = user
            })
        }

        return localList
    }

    suspend fun insert(user: User) {
        userDao.insert(user)
    }

    suspend fun delete(user: User) {
        userDao.delete(user)
    }

//    (("안"[0].code-0xAC00)/28)/21
    private fun getInitialIndex(initial: Char): String {
        val koreanInitial = arrayOf("ㄱ","ㄲ","ㄴ","ㄷ","ㄸ","ㄹ","ㅁ","ㅂ","ㅃ","ㅅ","ㅆ","ㅇ","ㅈ","ㅉ","ㅊ","ㅋ","ㅌ","ㅍ","ㅎ")
        return when (initial.code) {
            in 48..57 -> initial.toString()     // number
            in 65..122 -> initial.toString()    // english
            else -> {                           // korean
                koreanInitial[((initial.code-0xAC00)/28)/21]
            }
        }
    }
}