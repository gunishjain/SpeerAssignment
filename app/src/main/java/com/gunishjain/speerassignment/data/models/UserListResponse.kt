package com.gunishjain.speerassignment.data.models

data class UserListResponse(
    val incomplete_results: Boolean,
    val users: List<User>,
    val total_count: Int
)