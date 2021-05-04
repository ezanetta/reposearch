package com.ezanetta.reposearch.search.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.ezanetta.reposearch.databinding.FooterLoadStateItemBinding
import com.ezanetta.reposearch.search.presentation.adapter.viewholder.RepoItemLoadStateViewHolder

class RepoLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<RepoItemLoadStateViewHolder>() {

    override fun onBindViewHolder(holder: RepoItemLoadStateViewHolder, loadState: LoadState) {
        holder.bindState(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState) =
        RepoItemLoadStateViewHolder(
            FooterLoadStateItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            retry
        )
}