package com.ezanetta.reposearch.search.domain.usecase

import com.ezanetta.reposearch.search.data.model.RepoItem
import com.ezanetta.reposearch.search.data.model.Result
import com.ezanetta.reposearch.search.data.networking.RepoApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchRepoUseCaseImpl @Inject constructor(
    private val itemsApiService: RepoApiService
) : SearchRepoUseCase {

    override suspend fun search(query: String): Flow<Result<List<RepoItem>>> {
        return flow {
            itemsApiService.getReposByQuery(query, 0).collect { response ->
                when (response) {
                    is Result.Success -> emit(Result.Success(response.data))
                    is Result.Failure -> emit(Result.Failure)
                }
            }
        }
    }
}