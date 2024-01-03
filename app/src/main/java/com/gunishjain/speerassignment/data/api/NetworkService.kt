package com.gunishjain.speerassignment.data.api

import com.gunishjain.speerassignment.data.models.UserDetail
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Singleton

@Singleton
interface NetworkService {

    @GET("users/{username}")
    suspend fun getUserDetail(@Path("username") username:String) : UserDetail

    @GET("users/{username}/followers")
    suspend fun getFollowersList(@Path("username") username:String) : List<UserDetail>

    @GET("users/{username}/following")
    suspend fun getFollowingList(@Path("username") username:String) : List<UserDetail>

}