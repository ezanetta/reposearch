package com.ezanetta.reposearch.search.domain.usecase

import androidx.paging.PagingData
import com.ezanetta.reposearch.search.data.model.RepoItem
import com.ezanetta.reposearch.search.domain.repositories.GithubRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRepoUseCaseImpl @Inject constructor(
    private val githubRepository: GithubRepository
) : SearchRepoUseCase {

    override fun search(query: String): Flow<PagingData<RepoItem>> {
        return githubRepository.search(query)
    }
}