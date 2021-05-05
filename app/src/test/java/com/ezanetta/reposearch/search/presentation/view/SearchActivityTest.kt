package com.ezanetta.reposearch.search.presentation.view

import android.os.Build
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.paging.LoadState
import com.ezanetta.reposearch.R
import com.ezanetta.reposearch.search.presentation.SearchActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
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
    fun `processLoadState SHOULD show a loading spinner WHEN LoadState is Loading`() {
        // GIVEN
        val loading = shadowActivity.findViewById<ProgressBar>(R.id.loading)

        // WHEN
        shadowActivity.processLoadState(LoadState.Loading)

        // THEN
        assertTrue(loading.visibility == View.VISIBLE)
    }

    @Test
    fun `processLoadState SHOULD hide loading spinner when LoadState is NotLoading`() {
        // GIVEN
        val loading = shadowActivity.findViewById<ProgressBar>(R.id.loading)

        // WHEN
        shadowActivity.processLoadState(LoadState.NotLoading(false))

        // THEN
        assertTrue(loading.visibility == View.GONE)
    }

    @Test
    fun `processLoadState SHOULD show retry button when LoadState is Error`() {
        // GIVEN
        val retryButton = shadowActivity.findViewById<Button>(R.id.retry_button)

        // WHEN
        shadowActivity.processLoadState(LoadState.Error(Throwable()))

        // THEN
        assertTrue(retryButton.visibility == View.VISIBLE)
    }

    @Test
    fun `processLoadState SHOULD show error message text when LoadState is Error`() {
        // GIVEN
        val errorMsg = shadowActivity.findViewById<TextView>(R.id.error_msg)

        // WHEN
        shadowActivity.processLoadState(LoadState.Error(Throwable()))

        // THEN
        assertTrue(errorMsg.visibility == View.VISIBLE)
    }
}