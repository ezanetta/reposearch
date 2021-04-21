package com.ezanetta.reposearch.search.presentation.viewModel

import androidx.lifecycle.*
import com.ezanetta.reposearch.search.data.model.RepoItem
import com.ezanetta.reposearch.search.data.model.Result
import com.ezanetta.reposearch.search.domain.usecase.SearchRepositoriesUseCase
import com.ezanetta.reposearch.search.presentation.model.SearchActivityState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepoViewModel @Inject constructor(
    private val repositoriesUseCase: SearchRepositoriesUseCase
) : ViewModel() {

    val searchActivityState: LiveData<SearchActivityState> get() = _searchActivityState
    private val _searchActivityState: MutableLiveData<SearchActivityState> = MutableLiveData()

    fun fetchRepos(query: String) {
        viewModelScope.launch {
            repositoriesUseCase.search(query).collect { response ->
                when (response) {
                    is Result.Success -> _searchActivityState.postValue(
                        SearchActivityState.ShowRepos(
                            response.data as ArrayList<RepoItem>
                        )
                    )
                    is Result.Failure ->
                        _searchActivityState.postValue(SearchActivityState.ShowErrorState)
                }
            }
        }
    }
}