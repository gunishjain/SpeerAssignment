package com.gunishjain.speerassignment.data.models

import com.google.gson.annotations.SerializedName

data class UserDetail(
    val avatar_url: String,
    val bio: String,
    val blog: String,
    val company: String,
    val email: Any,
    val followers: Int,
    val followers_url: String,
    val following: Int,
    val following_url: String,
    val html_url: String,
    val id: Int,
    val location: String,
    @SerializedName("login")
    val username: String,
    val name: String,
    val node_id: String,
    val organizations_url: String,
    val public_gists: Int,
    val public_repos: Int,
    val repos_url: String,
    val starred_url: String,
    val url: String
)