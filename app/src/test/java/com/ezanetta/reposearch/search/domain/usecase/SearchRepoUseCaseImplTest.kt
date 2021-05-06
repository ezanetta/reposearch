package com.ezanetta.reposearch.search.domain.usecase

import androidx.paging.PagingData
import app.cash.turbine.test
import com.ezanetta.reposearch.search.data.model.Owner
import com.ezanetta.reposearch.search.data.model.RepoItem
import com.ezanetta.reposearch.search.domain.repositories.GithubRepository
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
class SearchRepoUseCaseImplTest {

    private val githubRepository: GithubRepository = mockk()
    private val searchRepoUseCaseImpl = SearchRepoUseCaseImpl(githubRepository)

    @ExperimentalTime
    @Test
    fun `search SHOULD return a flow with paging data`() {
        runBlocking {
            // GIVEN
            val actual = pagingDataRepoList

            every {
                githubRepository.search(USERNAME)
            } returns flow {
                emit(pagingDataRepoList)
            }

            // WHEN
            searchRepoUseCaseImpl.search(USERNAME).test {
                // THEN
                assertEquals(actual, expectItem())
                expectComplete()
            }
        }
    }

    private companion object {
        const val USERNAME = "Google"

        val repoList = arrayListOf(
            RepoItem("Fake repo", Owner("testUrl")),
            RepoItem("Another fake repo", Owner("testUrl")),
        )

        val pagingDataRepoList = PagingData.from(repoList)
    }
}