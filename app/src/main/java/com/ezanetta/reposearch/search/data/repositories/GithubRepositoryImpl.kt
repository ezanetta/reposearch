package com.ezanetta.reposearch.search.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ezanetta.reposearch.search.data.model.RepoItem
import com.ezanetta.reposearch.search.data.networking.RepoApiClient
import com.ezanetta.reposearch.search.data.source.GithubRepoPagingSource
import com.ezanetta.reposearch.search.domain.repositories.GithubRepository
import kotlinx.coroutines.flow.Flow

class GithubRepositoryImpl(
    private val apiClient: RepoApiClient
) : GithubRepository {

    override fun search(username: String): Flow<PagingData<RepoItem>> {
        return Pager(
            pagingSourceFactory = { GithubRepoPagingSource(apiClient, username) },
            config = PagingConfig(pageSize = 20)
        ).flow
    }
}