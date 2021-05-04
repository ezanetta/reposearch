package com.ezanetta.reposearch.search.domain.usecase

import androidx.paging.PagingData
import com.ezanetta.reposearch.search.data.model.RepoItem
import kotlinx.coroutines.flow.Flow

interface SearchRepoUseCase {
    fun search(query: String) : Flow<PagingData<RepoItem>>
}