package com.ezanetta.reposearch.search.di

import com.ezanetta.reposearch.search.data.networking.RepoApiClient
import com.ezanetta.reposearch.search.data.networking.RepoApiService
import com.ezanetta.reposearch.search.data.networking.RepoApiServiceImpl
import com.ezanetta.reposearch.search.util.GITHUB_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideRepoApiClient() : RepoApiClient {
        return Retrofit.Builder()
            .baseUrl(GITHUB_BASE_URL)
            .client(OkHttpClient())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(RepoApiClient::class.java)
    }

    @Provides
    fun providesApiService(repoApiClient: RepoApiClient) : RepoApiService {
        return RepoApiServiceImpl(repoApiClient)
    }
}