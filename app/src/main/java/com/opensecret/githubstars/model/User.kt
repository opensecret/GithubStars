package com.opensecret.githubstars.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(primaryKeys = ["id"])
data class User(

    @field:SerializedName("login")
    var login: String,

    @field:SerializedName("id")
    var id: Int,

    @field:SerializedName("avatar_url")
    var avatarUrl: String,

    @field:SerializedName("html_url")
    var htmlUrl: String,

    @field:SerializedName("favorite")
    var favorite: Boolean
)
