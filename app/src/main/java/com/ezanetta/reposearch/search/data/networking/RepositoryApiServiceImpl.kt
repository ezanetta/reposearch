package com.ezanetta.reposearch.search.data.networking

import com.ezanetta.reposearch.search.data.model.RepositoryItem
import com.ezanetta.reposearch.search.data.model.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoryApiServiceImpl @Inject constructor(
    private val apiClient: RepositoryApiClient
) : RepositoryApiService {

    override fun getReposByQuery(
        query: String,
        currentPage: Int
    ): Flow<Result<List<RepositoryItem>>> {
        return flow {
            apiClient.searchReposByUsername(query).takeIf {
                it.isNotEmpty()
            }?.run {
                emit(
                    Result.Success(this)
                )
            } ?: run {
                emit(Result.Failure)
            }
        }.catch {
            emit(Result.Failure)
        }
    }
}