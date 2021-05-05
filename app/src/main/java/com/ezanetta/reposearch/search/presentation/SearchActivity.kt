package com.ezanetta.reposearch.search.presentation

import android.os.Bundle
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.ezanetta.reposearch.databinding.ActivitySearchBinding
import com.ezanetta.reposearch.search.presentation.adapter.RepoAdapter
import com.ezanetta.reposearch.search.presentation.adapter.RepoLoadStateAdapter
import com.ezanetta.reposearch.search.presentation.viewModel.RepoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {

    private val searchViewModel: RepoViewModel by viewModels()
    private lateinit var binding: ActivitySearchBinding
    private val repoAdapter = RepoAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setupViews()
        listenSearch()
        setContentView(binding.root)
    }

    private fun listenSearch() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                lifecycleScope.launch {
                    query?.let {
                        searchViewModel.fetchRepos(it).collect { pagingData ->
                            repoAdapter.submitData(pagingData)
                        }
                    }
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })
    }

    private fun setupViews() {
        binding.retryButton.setOnClickListener { repoAdapter.retry() }

        binding.repoList.adapter =
            repoAdapter.withLoadStateFooter(RepoLoadStateAdapter { repoAdapter.retry() })

        repoAdapter.addLoadStateListener {
            processLoadState(it.source.refresh)
        }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun processLoadState(loadState: LoadState) {
        binding.apply {
            loading.isVisible = loadState is LoadState.Loading
            repoList.isVisible = loadState is LoadState.NotLoading
            retryButton.isVisible = loadState is LoadState.Error
            errorMsg.isVisible = loadState is LoadState.Error
        }
    }
}