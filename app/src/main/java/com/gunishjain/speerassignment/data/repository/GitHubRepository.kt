package com.gunishjain.speerassignment.data.repository

import com.gunishjain.speerassignment.data.api.NetworkService
import com.gunishjain.speerassignment.data.models.User
import com.gunishjain.speerassignment.data.models.UserDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GitHubRepository @Inject constructor(private val networkService: NetworkService) {
    fun getUserDetail(username: String): Flow<UserDetail> {
        return flow {
            emit(networkService.getUserDetail(username))
        }
    }

    fun getSearchResult(query: String,inside: String,type:String): Flow<List<User>> {
        return flow {
            emit(networkService.getSearchResult(query, inside, type))
        }.map {
            it.users
        }
    }

    fun getFollowersList(username: String) : Flow<List<User>> {
        return flow {
            emit(networkService.getFollowersList(username))
        }
    }

    fun getFollowingList(username: String) : Flow<List<User>> {
        return flow {
            emit(networkService.getFollowingList(username))
        }
    }


}