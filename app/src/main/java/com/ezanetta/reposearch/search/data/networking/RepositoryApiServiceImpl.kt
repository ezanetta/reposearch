package com.ezanetta.reposearch.search.data.networking

import com.ezanetta.reposearch.search.data.model.RepositoryItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoryApiServiceImpl @Inject constructor(
    private val apiClient: RepositoryApiClient
) : RepositoryApiService {

    override fun getReposByQuery(query: String, currentPage: Int): Flow<List<RepositoryItem>> {
        return flow {
            apiClient.searchReposByUsername(query).run {
                emit(this)
            }
        }
    }
}