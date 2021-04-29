package com.ezanetta.reposearch.search.presentation.view

import android.os.Build
import android.view.View
import android.widget.ProgressBar
import android.widget.Spinner
import androidx.appcompat.widget.SearchView
import com.ezanetta.reposearch.R
import com.ezanetta.reposearch.search.data.model.Owner
import com.ezanetta.reposearch.search.data.model.RepoItem
import com.ezanetta.reposearch.search.presentation.SearchActivity
import com.ezanetta.reposearch.search.presentation.model.SearchActivityState
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.any
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config


@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(application = HiltTestApplication::class, sdk = [Build.VERSION_CODES.P])
class SearchActivityTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private lateinit var shadowActivity: SearchActivity

    @Before
    fun setup() {
        hiltRule.inject()

        shadowActivity =
            Robolectric.buildActivity(SearchActivity::class.java).create().resume().get()
    }

    @Test
    fun `searchView SHOULD have a valid query hint set`() {
        // GIVEN
        val expectedQueryHint = "Search repositories by username"
        val textView = shadowActivity.findViewById<SearchView>(R.id.search_view)

        // WHEN

        // THEN
        assertEquals(expectedQueryHint, textView.queryHint)
    }

    @Test
    fun `processActivityState SHOULD show a loading spinner while networking call is processed`() {
        // GIVEN
        val loading = shadowActivity.findViewById<ProgressBar>(R.id.loading)

        // WHEN
        shadowActivity.processActivityState(SearchActivityState.ShowLoading)

        // THEN
        assertTrue(loading.visibility == View.VISIBLE)
    }

    @Test
    fun `processActivityState SHOULD hide loading spinner when SearchActivityState is ShowRepos`() {
        // GIVEN
        val loading = shadowActivity.findViewById<ProgressBar>(R.id.loading)

        // WHEN
        shadowActivity.processActivityState(SearchActivityState.ShowRepos(repoList))

        // THEN
        assertTrue(loading.visibility == View.GONE)
    }

    private companion object {
        val repoList = arrayListOf(RepoItem("Fake repo", Owner("testUrl")))
    }
}