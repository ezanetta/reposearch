package com.ezanetta.reposearch.search.presentation

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.ezanetta.reposearch.R
import com.ezanetta.reposearch.databinding.ActivitySearchBinding
import com.ezanetta.reposearch.search.presentation.adapter.RepoAdapter
import com.ezanetta.reposearch.search.presentation.viewModel.RepoViewModel
import com.ezanetta.reposearch.search.util.showKeyboard
import com.google.android.material.snackbar.Snackbar
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
        binding.repoList.adapter = repoAdapter
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