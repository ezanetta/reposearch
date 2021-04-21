package com.ezanetta.reposearch.search.data.networking

import com.ezanetta.reposearch.search.data.model.RepositoryItem
import kotlinx.coroutines.flow.Flow

interface RepositoryApiService {
    fun getReposByQuery(query: String, currentPage: Int): Flow<List<RepositoryItem>>
}