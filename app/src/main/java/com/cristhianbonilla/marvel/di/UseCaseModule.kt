package com.cristhianbonilla.marvel.di

import com.cristhianbonilla.domain.characters.usecase.GetCharacterByIdUseCase
import com.cristhianbonilla.domain.characters.usecase.GetCharacterByIdUseCaseImpl
import com.cristhianbonilla.domain.characters.usecase.GetCharactersListUseCase
import com.cristhianbonilla.domain.characters.usecase.GetCharactersListUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {
    @Singleton
    @Binds
    abstract fun provideGetCharactersListUseCase(impl: GetCharactersListUseCaseImpl): GetCharactersListUseCase

    @Singleton
    @Binds
    abstract fun provideGetCharacterByIdeUseCase(impl: GetCharacterByIdUseCaseImpl): GetCharacterByIdUseCase
}
