package com.gunishjain.speerassignment.data.api

import com.gunishjain.speerassignment.data.models.User
import com.gunishjain.speerassignment.data.models.UserDetail
import com.gunishjain.speerassignment.data.models.UserListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface NetworkService {

    @GET("users/{username}")
    suspend fun getUserDetail(@Path("username") username: String): UserDetail

    @GET("search/users")
    suspend fun getSearchResult(
        @Query("q") query: String, @Query("in") inside: String, @Query("type") type: String
    ): UserListResponse

    @GET("users/{username}/followers")
    suspend fun getFollowersList(@Path("username") username: String): List<User>

    @GET("users/{username}/following")
    suspend fun getFollowingList(@Path("username") username: String): List<User>

}