package com.ezanetta.reposearch.search.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.activity.viewModels
import com.ezanetta.reposearch.R
import com.ezanetta.reposearch.databinding.ActivitySearchBinding
import com.ezanetta.reposearch.search.presentation.adapter.RepoAdapter
import com.ezanetta.reposearch.search.presentation.model.SearchActivityState
import com.ezanetta.reposearch.search.presentation.viewModel.RepoViewModel
import com.ezanetta.reposearch.search.util.closeKeyboard
import com.ezanetta.reposearch.search.util.hide
import com.ezanetta.reposearch.search.util.show
import com.ezanetta.reposearch.search.util.showKeyboard
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {

    private val searchViewModel: RepoViewModel by viewModels()
    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setupObserver()
        listenSearch()
        setContentView(binding.root)
    }

    private fun listenSearch() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { searchViewModel.fetchRepos(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })
    }

    private fun setupObserver() {
        searchViewModel.searchActivityState.observe(this, ::processActivityState)
    }
    
    private fun processActivityState(state: SearchActivityState) {
        when (state) {
            is SearchActivityState.ShowRepos -> {
                binding.loading.hide()
                binding.repoList.adapter = RepoAdapter(state.repos)
            }
            SearchActivityState.ShowErrorState -> {
                binding.loading.hide()
                showErrorMessage()
                closeKeyboard()
            }
            SearchActivityState.ShowLoading -> {
                binding.loading.show()
            }
        }
    }

    private fun showErrorMessage() {
        val snackbar = Snackbar
            .make(
                binding.container,
                getString(R.string.error_message),
                Snackbar.LENGTH_LONG
            )

        snackbar.setAction(getString(R.string.search_try_again)) {
            actionListener(it)
        }

        snackbar.show()
    }

    private fun actionListener(view: View) {
        binding.searchView.apply {
            setQuery("", false)
            requestFocus()
            showKeyboard()
        }
    }
}