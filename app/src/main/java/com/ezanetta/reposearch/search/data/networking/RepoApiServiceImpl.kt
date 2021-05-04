package com.ezanetta.reposearch.search.data.networking

import com.ezanetta.reposearch.search.data.model.RepoItem
import com.ezanetta.reposearch.search.data.model.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepoApiServiceImpl @Inject constructor(
    private val apiClient: RepoApiClient
) : RepoApiService {

    override fun getReposByQuery(
        query: String,
        page: Int,
        size: Int
    ): Flow<Result<List<RepoItem>>> {
        return flow {
            apiClient.searchReposByUsername(query, page, size).takeIf {
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