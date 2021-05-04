package com.ezanetta.reposearch.search.presentation.adapter.viewholder

import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.ezanetta.reposearch.databinding.FooterLoadStateItemBinding

class RepoItemLoadStateViewHolder(
    private val binding: FooterLoadStateItemBinding,
    retry: () -> Unit
): RecyclerView.ViewHolder(binding.root) {

    init {
        binding.retryButton.setOnClickListener { retry() }
    }

    fun bindState(loadState: LoadState) = with(binding) {

        if (loadState is LoadState.Error) {
            errorMsg.text = loadState.error.localizedMessage
        }

        progressBar.isVisible = loadState is LoadState.Loading
        retryButton.isVisible = loadState is LoadState.Error
        errorMsg.isVisible = loadState is LoadState.Error
    }
}