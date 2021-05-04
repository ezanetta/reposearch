package com.ezanetta.reposearch.search.domain.repositories

import androidx.paging.PagingData
import com.ezanetta.reposearch.search.data.model.RepoItem
import kotlinx.coroutines.flow.Flow

interface GithubRepository {
    fun search(username: String): Flow<PagingData<RepoItem>>
}