package com.ezanetta.reposearch.search.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.viewModels
import com.ezanetta.reposearch.R
import com.ezanetta.reposearch.search.presentation.viewModel.RepoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {

    private val searchViewModel: RepoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        setupObserver()
        search()
    }

    private fun setupObserver() {
        searchViewModel.repos.observe(this, {
            it.forEach { repo ->
                Log.d("Dale wacho", "Repo name ${repo.name}")
            }
        })
    }

    private fun search() {
        searchViewModel.fetchRepos("Google")
    }
}