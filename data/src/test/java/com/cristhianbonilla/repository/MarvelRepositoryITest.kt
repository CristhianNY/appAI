package com.cristhianbonilla.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.cristhianbonilla.TestCoroutineRule
import com.cristhianbonilla.data.characters.data_source.MarvelDataSource
import com.cristhianbonilla.data.characters.entity.detail.CharacterDetailResponseEntity
import com.cristhianbonilla.data.characters.entity.detail.toModel
import com.cristhianbonilla.data.characters.entity.list.CharacterResponseEntity
import com.cristhianbonilla.data.characters.entity.list.toModel
import com.cristhianbonilla.data.characters.repository.MarvelRepositoryImpl
import com.cristhianbonilla.domain.characters.repository.MarvelRepository
import com.cristhianbonilla.mocks.characterDetailResponseEntity
import com.cristhianbonilla.mocks.characterListResultEntityMock
import com.cristhianbonilla.support.config.GenericError
import com.cristhianbonilla.support.config.Result
import com.cristhianbonilla.support.config.ResultDomain
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.net.HttpURLConnection

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MarvelRepositoryITest {

    @Rule
    @JvmField
    val testRule: TestRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val testCoroutineRule: TestCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var dataSource: MarvelDataSource

    private lateinit var repository: MarvelRepository

    @Before
    fun setUp() {
        repository = MarvelRepositoryImpl(dataSource)
    }

    @Test
    fun `Get marvel characters list success`() = testCoroutineRule.runBlockingTest {
        val expectedResult = ResultDomain.Success(characterListResultEntityMock.toModel())

        // given
        whenever(dataSource.getCharacterList()).thenReturn(
            Result.success(
                characterListResultEntityMock
            )
        )

        // when
        val result = repository.getCharacterList()

        // then
        verify(dataSource).getCharacterList()
        Assert.assertEquals(expectedResult, result)
    }

    @Test
    fun `Get marvel characters list error`() = testCoroutineRule.runBlockingTest {
        val error = Result.error<CharacterResponseEntity>(
            Exception(),
            HttpURLConnection.HTTP_INTERNAL_ERROR
        )

        // given
        whenever(dataSource.getCharacterList()).thenReturn(error)

        // when
        val result = repository.getCharacterList()

        // then
        verify(dataSource).getCharacterList()
        Assert.assertTrue(result is ResultDomain.Error)
        Assert.assertTrue((result as ResultDomain.Error)?.error == GenericError)
    }

    @Test
    fun `Get marvel character details success`() = testCoroutineRule.runBlockingTest {
        val characterId = "1"
        val expectedResult = ResultDomain.Success(characterDetailResponseEntity.toModel())

        // given
        whenever(dataSource.getCharacterById(characterId)).thenReturn(
            Result.success(
                characterDetailResponseEntity
            )
        )

        // when
        val result = repository.getCharacterById(characterId)

        // then
        verify(dataSource).getCharacterById(characterId)
        Assert.assertEquals(expectedResult, result)
    }

    @Test
    fun `Get marvel character details error`() = testCoroutineRule.runBlockingTest {
        val characterId = "1"
        val error = Result.error<CharacterDetailResponseEntity>(
            Exception(),
            HttpURLConnection.HTTP_INTERNAL_ERROR
        )

        // given
        whenever(dataSource.getCharacterById(characterId)).thenReturn(error)

        // when
        val result = repository.getCharacterById(characterId)

        // then
        verify(dataSource).getCharacterById(characterId)
        Assert.assertTrue(result is ResultDomain.Error)
        Assert.assertTrue((result as ResultDomain.Error)?.error == GenericError)
    }
}
