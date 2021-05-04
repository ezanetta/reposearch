package com.ezanetta.reposearch.search.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ezanetta.reposearch.search.data.model.RepoItem
import com.ezanetta.reposearch.search.domain.usecase.SearchRepoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class RepoViewModel @Inject constructor(
    private val repoUseCase: SearchRepoUseCase
) : ViewModel() {

    fun fetchRepos(query: String) : Flow<PagingData<RepoItem>> {
        return  repoUseCase.search(query).apply {
            cachedIn(viewModelScope)
        }
    }
}