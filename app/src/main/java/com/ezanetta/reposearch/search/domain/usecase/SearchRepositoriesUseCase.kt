package com.ezanetta.reposearch.search.domain.usecase

import com.ezanetta.reposearch.search.data.model.RepositoryItem
import com.ezanetta.reposearch.search.data.model.Result
import kotlinx.coroutines.flow.Flow

interface SearchRepositoriesUseCase {
    suspend fun search(query: String) : Flow<Result<List<RepositoryItem>>>
}