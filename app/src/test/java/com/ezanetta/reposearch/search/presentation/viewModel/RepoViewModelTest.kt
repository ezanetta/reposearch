package com.ezanetta.reposearch.search.presentation.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ezanetta.reposearch.search.data.model.Owner
import com.ezanetta.reposearch.search.data.model.RepoItem
import com.ezanetta.reposearch.search.data.model.Result
import com.ezanetta.reposearch.search.domain.usecase.SearchRepoUseCaseImpl
import com.ezanetta.reposearch.search.presentation.model.SearchActivityState
import com.ezanetta.reposearch.util.TestCoroutineRule
import com.ezanetta.reposearch.util.getOrAwaitValue
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class RepoViewModelTest {

    private val searchRepoUseCase: SearchRepoUseCaseImpl = mockk()
    private val repoViewModel = RepoViewModel(searchRepoUseCase)

    @get:Rule
    var testCoroutineRule = TestCoroutineRule()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        // no-op
    }

    @After
    fun tearDown() {
        // no-op
    }

    @Test
    fun `fetchRepos SHOULD call searchActivityState liveData with ShowRepos value`() {
        testCoroutineRule.runBlockingTest {

            // GIVEN
            coEvery {
                searchRepoUseCase.search(any())
            } returns repoList

            // WHEN
            repoViewModel.fetchRepos(USERNAME)

            // THEN
            assertTrue(repoViewModel.searchActivityState.getOrAwaitValue() is SearchActivityState.ShowRepos)
        }
    }

    @Test
    fun `fetchRepos SHOULD call searchActivityState liveData with ShowErrorState value`() {
        testCoroutineRule.runBlockingTest {

            // GIVEN
            coEvery {
                searchRepoUseCase.search(any())
            } returns failureResponse

            // WHEN
            repoViewModel.fetchRepos(USERNAME)

            // THEN
            assertTrue(repoViewModel.searchActivityState.getOrAwaitValue() is SearchActivityState.ShowErrorState)
        }
    }

    private companion object {
        const val USERNAME = "Google"

        val repoList = flow {
            emit(
                Result.Success(
                    arrayListOf(
                        RepoItem("Fake repo", Owner("testUrl")),
                        RepoItem("Another fake repo", Owner("testUrl")),
                    )
                )
            )
        }

        val failureResponse = flow { emit(Result.Failure) }
    }
}