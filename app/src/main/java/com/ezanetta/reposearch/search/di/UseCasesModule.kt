package com.ezanetta.reposearch.search.di

import com.ezanetta.reposearch.search.data.networking.RepoApiClient
import com.ezanetta.reposearch.search.data.repositories.GithubRepositoryImpl
import com.ezanetta.reposearch.search.domain.repositories.GithubRepository
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
    fun provideGithubRepository(repoApiClient: RepoApiClient): GithubRepository {
        return GithubRepositoryImpl(repoApiClient)
    }

    @Provides
    fun provideSearchUseCase(githubRepository: GithubRepository): SearchRepoUseCase {
        return SearchRepoUseCaseImpl(githubRepository)
    }
}