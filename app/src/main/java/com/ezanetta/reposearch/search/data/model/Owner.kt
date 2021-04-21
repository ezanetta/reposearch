package com.ezanetta.reposearch.search.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Owner(@Json(name = "avatar_url") val avatarUrl: String)