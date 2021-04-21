package com.ezanetta.reposearch.search.presentation.model

import com.ezanetta.reposearch.search.data.model.RepoItem

sealed class SearchActivityState {
    class ShowRepos(val repos: ArrayList<RepoItem>) : SearchActivityState()
    object ShowErrorState : SearchActivityState()
}