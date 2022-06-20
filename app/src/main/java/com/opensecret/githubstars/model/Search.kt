package com.opensecret.githubstars.model

import com.google.gson.annotations.SerializedName

data class Search(

    @SerializedName("total_count")
    var totalCount: Int,

    @SerializedName("incomplete_results")
    var incompleteResults: Boolean,

    @SerializedName("items")
    var items: List<User>
)
