package com.opensecret.githubstars.dao

import androidx.room.*
import com.opensecret.githubstars.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM User")
    suspend fun getAll(): List<User>

    @Query("SELECT * FROM User WHERE login LIKE :query")
    suspend fun getUserByQuery(query: String): List<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Delete
    suspend fun delete(user: User)

//    @Query("DELETE FROM User WHERE login = :login")
//    suspend fun deleteUserByLogin(login: String)
}