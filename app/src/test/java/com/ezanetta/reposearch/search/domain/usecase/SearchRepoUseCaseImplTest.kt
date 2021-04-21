package com.ezanetta.reposearch.search.domain.usecase

import com.ezanetta.reposearch.search.data.model.Owner
import com.ezanetta.reposearch.search.data.model.RepoItem
import com.ezanetta.reposearch.search.data.model.Result
import com.ezanetta.reposearch.search.data.networking.RepoApiService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.Assert.assertTrue

@ExperimentalCoroutinesApi
class SearchRepoUseCaseImplTest {

    private val repoApiService: RepoApiService = mockk()
    private val searchRepoUseCaseImpl = SearchRepoUseCaseImpl(repoApiService)

    @Test
    fun `search SHOULD return a Success response with a list of RepoItem`() {
        runBlocking {
            // GIVEN
             coEvery {
                 repoApiService.getReposByQuery(any(), any())
             } returns successResponse

            // WHEN
            val response = searchRepoUseCaseImpl.search(USERNAME)

            // THEN
            response.collect { itResponse ->
                assertTrue(itResponse is Result.Success)
                assertTrue((itResponse as Result.Success).data.isNotEmpty())
            }
        }
    }

    @Test
    fun `search SHOULD return a Failure response WHEN receive a Failure from ApiService`() {
        runBlocking {
            // GIVEN
            coEvery {
                repoApiService.getReposByQuery(any(), any())
            } returns failureResponse

            // WHEN
            val response = searchRepoUseCaseImpl.search(USERNAME)

            // THEN
            response.collect { itResponse ->
                assertTrue(itResponse is Result.Failure)
            }
        }
    }

    private companion object {
        const val USERNAME = "Google"
        val repoList = listOf(RepoItem("Fake repo", Owner("testUrl")))
        val successResponse = flow { emit(Result.Success(repoList)) }
        val failureResponse = flow { emit(Result.Failure) }
    }
}