package com.gunishjain.speerassignment.data.repository

import com.gunishjain.speerassignment.data.api.NetworkService
import com.gunishjain.speerassignment.data.models.UserDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GitHubRepository @Inject constructor(private val networkService: NetworkService) {
    fun getUserDetail(username: String): Flow<UserDetail> {
        return flow {
            emit(networkService.getUserDetail(username))
        }
    }


}