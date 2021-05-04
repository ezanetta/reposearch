package com.ezanetta.reposearch.search.data.networking

import com.ezanetta.reposearch.search.data.model.Owner
import com.ezanetta.reposearch.search.data.model.RepoItem
import com.ezanetta.reposearch.search.data.model.Result
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Test

@ExperimentalCoroutinesApi
class RepoApiServiceImplTest {

    private val apiClient: RepoApiClient = mockk()
    private val repoApiServiceImpl = RepoApiServiceImpl(apiClient)

    @Test
    fun `getReposByQuery SHOULD return a list of RepoItem WHEN response is Success`() {
        runBlocking {
            // GIVEN
            coEvery {
                apiClient.searchReposByUsername(any(), any(), any())
            } returns repoList

            // WHEN
            val response: Flow<Result<List<RepoItem>>> =
                repoApiServiceImpl.getReposByQuery(USERNAME, FIRST_PAGE)

            // THEN
            response.collect { result ->
                assertTrue(result is Result.Success)
            }
        }
    }

    @Test
    fun `getReposByQuery SHOULD return a Failure response when an error occurs`() {
        runBlocking {
            // GIVEN
            coEvery {
                apiClient.searchReposByUsername(any(), any(), any())
            } throws Exception()

            // WHEN
            val response: Flow<Result<List<RepoItem>>> =
                repoApiServiceImpl.getReposByQuery(USERNAME, FIRST_PAGE)

            // THEN
            response.collect {
                assertTrue(it is Result.Failure)
            }
        }
    }

    private companion object {
        const val USERNAME = "Google"
        const val FIRST_PAGE = 0
        val repoList = listOf(RepoItem("Fake repo", Owner("testUrl")))
    }
}