package com.ezanetta.reposearch.search.presentation.model

import com.ezanetta.reposearch.search.data.model.RepositoryItem

sealed class SearchActivityState {
    class ShowRepos(val repos: ArrayList<RepositoryItem>) : SearchActivityState()
    object ShowErrorState : SearchActivityState()
}