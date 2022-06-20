package com.opensecret.githubstars.network

import com.opensecret.githubstars.model.Search
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    // search/users?q={query}&per_page=100&page=1
    @GET("search/users")
    fun getSearchUsers(
        @Query("q") query: String?,
        @Query("per_page") perPage: Int = 100,
        @Query("page") page: Int = 1
    ): Call<Search>
}