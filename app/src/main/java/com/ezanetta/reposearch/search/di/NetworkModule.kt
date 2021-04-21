package com.ezanetta.reposearch.search.di

import com.ezanetta.reposearch.search.data.networking.RepositoryApiClient
import com.ezanetta.reposearch.search.data.networking.RepositoryApiService
import com.ezanetta.reposearch.search.data.networking.RepositoryApiServiceImpl
import com.ezanetta.reposearch.search.domain.usecase.SearchRepositoriesUseCase
import com.ezanetta.reposearch.search.domain.usecase.SearchRepositoriesUseCaseImpl
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
    fun provideRepoApiClient() : RepositoryApiClient {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .client(OkHttpClient())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(RepositoryApiClient::class.java)
    }

    @Provides
    fun providesApiService(repositoryApiClient: RepositoryApiClient) : RepositoryApiService {
        return RepositoryApiServiceImpl(repositoryApiClient)
    }
}