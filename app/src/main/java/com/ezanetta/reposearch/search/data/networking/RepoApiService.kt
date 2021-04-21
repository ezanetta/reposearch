package com.ezanetta.reposearch.search.data.networking

import com.ezanetta.reposearch.search.data.model.RepoItem
import com.ezanetta.reposearch.search.data.model.Result
import kotlinx.coroutines.flow.Flow

interface RepoApiService {
    fun getReposByQuery(query: String, currentPage: Int): Flow<Result<List<RepoItem>>>
}