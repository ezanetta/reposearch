package com.ezanetta.reposearch.search.data.networking

import com.ezanetta.reposearch.search.data.model.RepositoryItem
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface RepositoryApiClient {

    /**
     * List public repositories for the specified username.
     *
     * @param username - the username used to search repos.
     * @return a list of public repos.
     */
    @Headers("User-Agent: GitHubMVP-App")
    @GET("users/{username}/repos")
    suspend fun searchReposByUsername(@Path("username") username: String): List<RepositoryItem>
}