package com.ezanetta.reposearch.search.domain.usecase

import com.ezanetta.reposearch.search.data.model.RepositoryItem
import kotlinx.coroutines.flow.Flow

interface SearchRepositoriesUseCase {
    suspend fun search(query: String) : Flow<List<RepositoryItem>>
}