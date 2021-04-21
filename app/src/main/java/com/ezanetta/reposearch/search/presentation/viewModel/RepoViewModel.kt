package com.ezanetta.reposearch.search.presentation.viewModel

import androidx.lifecycle.*
import androidx.lifecycle.Transformations.map
import com.ezanetta.reposearch.search.data.model.RepositoryItem
import com.ezanetta.reposearch.search.domain.usecase.SearchRepositoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepoViewModel @Inject constructor(
    private val repositoriesUseCase: SearchRepositoriesUseCase
)  : ViewModel() {

    private val _repos: MutableLiveData<List<RepositoryItem>> = MutableLiveData()
    val repos: LiveData<List<RepositoryItem>> get() = _repos

    fun fetchRepos(query: String) {
        viewModelScope.launch {
            repositoriesUseCase.search(query).collect {
                _repos.postValue(it)
            }
        }
    }
}