package com.cristhianbonilla.appAI.di

import com.cristhianbonilla.data.characters.repository.MarvelRepositoryImpl
import com.cristhianbonilla.domain.characters.repository.MarvelRepository
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
    abstract fun provideRepositoryModule(impl: MarvelRepositoryImpl): MarvelRepository
}
