package com.ezanetta.reposearch.search.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.ezanetta.reposearch.search.data.model.RepositoryItem
import com.ezanetta.reposearch.search.data.networking.RepositoryApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class SearchRepositoriesUseCaseImpl @Inject constructor(
    private val itemsApiService: RepositoryApiService
) : SearchRepositoriesUseCase {

    override suspend fun search(query: String): Flow<List<RepositoryItem>> {
        return itemsApiService.getReposByQuery(query, 0)
    }
}