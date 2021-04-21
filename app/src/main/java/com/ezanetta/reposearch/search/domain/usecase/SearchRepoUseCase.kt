package com.ezanetta.reposearch.search.domain.usecase

import com.ezanetta.reposearch.search.data.model.RepoItem
import com.ezanetta.reposearch.search.data.model.Result
import kotlinx.coroutines.flow.Flow

interface SearchRepoUseCase {
    suspend fun search(query: String) : Flow<Result<List<RepoItem>>>
}