package com.cristhianbonilla.appai.di

import com.cristhianbonilla.data.chatgpt3.api.Gpt3APi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Singleton
    @Provides
    fun provideGPT3Api(retrofit: Retrofit): Gpt3APi {
        return retrofit.create(Gpt3APi::class.java)
    }
}
