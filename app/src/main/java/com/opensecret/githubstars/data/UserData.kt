package com.opensecret.githubstars.data

import com.opensecret.githubstars.model.User

class UserData : BaseData() {

    init {
        setType(BaseData.USER)
    }

    var user: User? = null
}