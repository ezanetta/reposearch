package com.ezanetta.reposearch.search.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ezanetta.reposearch.search.data.model.RepoItem
import com.ezanetta.reposearch.search.data.networking.RepoApiClient

class GithubRepoPagingSource(
    private val apiClient: RepoApiClient,
    private val username: String
) : PagingSource<Int, RepoItem>() {

    override fun getRefreshKey(state: PagingState<Int, RepoItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepoItem> {
        return try {
            val page = params.key ?: INITIAL_PAGE
            val response = apiClient.searchReposByUsername(username, page, params.loadSize)
            LoadResult.Page(
                data = response,
                prevKey = if (page == INITIAL_PAGE) null else page - ONE_PAGE,
                nextKey = if (response.isEmpty()) null else page + ONE_PAGE
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    private companion object {
        const val INITIAL_PAGE = 1
        const val ONE_PAGE = 1
    }
}