package com.ezanetta.reposearch.search.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.databinding.DataBindingUtil
import com.ezanetta.reposearch.R
import com.ezanetta.reposearch.databinding.ActivitySearchBinding
import com.ezanetta.reposearch.search.presentation.model.SearchActivityState
import com.ezanetta.reposearch.search.presentation.viewModel.RepoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {

    private val searchViewModel: RepoViewModel by viewModels()
    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)
        binding.viewModel = searchViewModel
        setupObserver()
        listenSearch()
    }

    private fun listenSearch() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            OnQueryTextListener {
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
        searchViewModel.searchActivityState.observe(this, { state ->
            when (state) {
                is SearchActivityState.ShowRepos -> {
                    binding.loading.visibility = View.GONE

                    state.repos.forEach { repo ->
                        // TODO: Setup RecyclerView
                    }
                }
                SearchActivityState.ShowErrorState -> {
                    // TODO: Show error view
                }
                SearchActivityState.ShowLoading -> {
                    binding.loading.visibility = View.VISIBLE
                }
            }
        })
    }
}