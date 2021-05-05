package com.ezanetta.reposearch.search.presentation.viewModel

import androidx.paging.PagingData
import app.cash.turbine.test
import com.ezanetta.reposearch.search.data.model.Owner
import com.ezanetta.reposearch.search.data.model.RepoItem
import com.ezanetta.reposearch.search.domain.usecase.SearchRepoUseCaseImpl
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalCoroutinesApi
class RepoViewModelTest {

    private val searchRepoUseCase: SearchRepoUseCaseImpl = mockk()
    private val repoViewModel = RepoViewModel(searchRepoUseCase)

    @Test
    fun `fetchRepos SHOULD return a flow with paging data`() {
        runBlocking {
            // GIVEN
            val actual = pagingDataRepoList

            every {
                searchRepoUseCase.search(USERNAME)
            } returns flow {
                emit(pagingDataRepoList)
            }

            // WHEN
            repoViewModel.fetchRepos(USERNAME).test {
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