package com.ezanetta.reposearch.search.di

import com.ezanetta.reposearch.search.data.networking.RepoApiService
import com.ezanetta.reposearch.search.domain.usecase.SearchRepositoriesUseCase
import com.ezanetta.reposearch.search.domain.usecase.SearchRepositoriesUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCasesModule {

    @Provides
    fun provideSearchUseCase(repoApiService: RepoApiService): SearchRepositoriesUseCase {
        return SearchRepositoriesUseCaseImpl(repoApiService)
    }
}