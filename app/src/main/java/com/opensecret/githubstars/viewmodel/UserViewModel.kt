package com.opensecret.githubstars.viewmodel

import androidx.lifecycle.*
import com.opensecret.githubstars.data.BaseData
import com.opensecret.githubstars.model.User
import com.opensecret.githubstars.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserViewModel @Inject constructor(val userRepository: UserRepository) : ViewModel() {

    private val _remoteList = MutableLiveData<List<BaseData>>()
    val remoteList: LiveData<List<BaseData>>
        get() = _remoteList
    private var _localList = MutableLiveData<List<BaseData>>()
    val localList: LiveData<List<BaseData>>
        get() = _localList

    fun searchRemoteUser(query: String) {
        viewModelScope.launch {
            _remoteList.value = userRepository.searchRemoteUser(query)
        }
    }

    fun searchLocalUser(query: String) {
        viewModelScope.launch {
            _localList.value = userRepository.searchLocalUser(query)
        }
    }

    fun addFavoriteUser(user: User) {
        viewModelScope.launch { userRepository.insert(user) }
    }

    fun removeFavoriteUser(user: User) {
        viewModelScope.launch { userRepository.delete(user) }
    }
}