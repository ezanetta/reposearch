package com.ezanetta.reposearch.search.di

import com.ezanetta.reposearch.search.data.networking.RepoApiService
import com.ezanetta.reposearch.search.domain.usecase.SearchRepoUseCase
import com.ezanetta.reposearch.search.domain.usecase.SearchRepoUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCasesModule {

    @Provides
    fun provideSearchUseCase(repoApiService: RepoApiService): SearchRepoUseCase {
        return SearchRepoUseCaseImpl(repoApiService)
    }
}