package com.ezanetta.reposearch.search.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RepoItem(val name: String, val owner: Owner)