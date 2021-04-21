package com.ezanetta.reposearch.search.data.networking

import com.ezanetta.reposearch.search.data.model.RepositoryItem
import com.ezanetta.reposearch.search.data.model.Result
import kotlinx.coroutines.flow.Flow

interface RepositoryApiService {
    fun getReposByQuery(query: String, currentPage: Int): Flow<Result<List<RepositoryItem>>>
}