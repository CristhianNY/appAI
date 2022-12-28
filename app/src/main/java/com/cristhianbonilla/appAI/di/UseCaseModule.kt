package com.cristhianbonilla.appai.di

import com.cristhianbonilla.domain.gpt3.usecase.RequestCompletionFromGpt3UseCase
import com.cristhianbonilla.domain.gpt3.usecase.RequestCompletionFromGpt3UseCaseImpl
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
    abstract fun provideRequestCompletionFromGpt3UseCase(impl: RequestCompletionFromGpt3UseCaseImpl): RequestCompletionFromGpt3UseCase
}
