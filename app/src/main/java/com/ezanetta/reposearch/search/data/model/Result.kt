package com.ezanetta.reposearch.search.data.model

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    object Failure: Result<Nothing>()
}