package com.gunishjain.speerassignment.data.models

import com.google.gson.annotations.SerializedName

data class UserListResponse(
    val incomplete_results: Boolean,
    @SerializedName("items")
    val users: List<User>,
    val total_count: Int
)