package com.example.mini2.data.remote


import retrofit2.http.GET

interface UserApi {
    @GET("users")
    suspend fun getUsers(): List<ApiUser>
}
