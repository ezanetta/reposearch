package com.ezanetta.reposearch.search.data.source

import androidx.paging.PagingSource.LoadParams
import androidx.paging.PagingSource.LoadResult
import com.ezanetta.reposearch.search.data.model.Owner
import com.ezanetta.reposearch.search.data.model.RepoItem
import com.ezanetta.reposearch.search.data.networking.RepoApiClient
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class GithubRepoPagingSourceTest {

    private val apiClient: RepoApiClient = mockk()
    private val githubRepoPagingSource = GithubRepoPagingSource(apiClient, USERNAME)

    @Test
    fun `initial load SHOULD return a list of valid items WHEN api client response success`() {
        runBlockingTest {
            // GIVEN
            coEvery {
                apiClient.searchReposByUsername(any(), any(), any())
            } returns repoList

            val params: LoadParams<Int> = LoadParams.Refresh(
                FIRST_PAGE,
                LOAD_SIZE,
                false
            )
            val expectedResult = LoadResult.Page(repoList, null, NEXT_PAGE)

            // WHEN
            val result = githubRepoPagingSource.load(params)

            // THEN
            assertEquals(expectedResult, result)
        }
    }

    @Test
    fun `initial load SHOULD return an error WHEN api client response fails`() {
        runBlockingTest {
            // GIVEN
            val exception = Exception("error")
            coEvery {
                apiClient.searchReposByUsername(any(), any(), any())
            } throws exception

            val params: LoadParams<Int> = LoadParams.Refresh(
                FIRST_PAGE,
                LOAD_SIZE,
                false
            )
            val expectedResult = LoadResult.Error<Int, RepoItem>(exception)

            // WHEN
            val result = githubRepoPagingSource.load(params)

            // THEN
            assertEquals(expectedResult, result)
        }
    }
    
    private companion object {
        const val USERNAME = "Google"
        const val FIRST_PAGE = 1
        const val NEXT_PAGE = 2
        const val LOAD_SIZE = 20
        val repoList = listOf(RepoItem("Fake repo", Owner("testUrl")))
    }

}