package com.ezanetta.reposearch.search.data.networking

import com.ezanetta.reposearch.search.data.model.RepoItem
import com.ezanetta.reposearch.search.data.model.Result
import kotlinx.coroutines.flow.Flow

interface RepoApiService {
    fun getReposByQuery(
        query: String,
        page: Int = 1,
        size: Int = 10
    ): Flow<Result<List<RepoItem>>>
}