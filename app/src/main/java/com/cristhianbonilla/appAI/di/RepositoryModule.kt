package com.cristhianbonilla.appai.di

import com.cristhianbonilla.data.chatgpt3.repository.Gpt3RepositoryImpl
import com.cristhianbonilla.domain.gpt3.repository.Gpt3Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun provideGpt3Repository(impl: Gpt3RepositoryImpl): Gpt3Repository
}
