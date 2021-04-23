package com.ezanetta.reposearch.search.util

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat


fun View.hide() {
    visibility = View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun AppCompatActivity.closeKeyboard() {
    val view: View? = this.currentFocus

    view?.let {
        val inputMethodManager: InputMethodManager? =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?

        inputMethodManager?.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

fun AppCompatActivity.showKeyboard() {
    val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
    inputMethodManager?.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0)
}