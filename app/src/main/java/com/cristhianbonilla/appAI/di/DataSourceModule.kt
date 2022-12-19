package com.cristhianbonilla.appAI.di

import com.cristhianbonilla.data.characters.data_source.MarvelDataSource
import com.cristhianbonilla.data.characters.data_source.MarvelDataSourceImpl
import com.cristhianbonilla.data.chatgpt3.data_source.Gpt3Datasource
import com.cristhianbonilla.data.chatgpt3.data_source.Gpt3DatasourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Singleton
    @Binds
    abstract fun provideMarvelDataSource(impl: MarvelDataSourceImpl): MarvelDataSource

    @Singleton
    @Binds
    abstract fun provideGpt3DataSource(impl: Gpt3DatasourceImpl): Gpt3Datasource
}
