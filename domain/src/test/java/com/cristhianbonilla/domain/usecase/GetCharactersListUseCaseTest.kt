package com.cristhianbonilla.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.cristhianbonilla.domain.TestCoroutineRule
import com.cristhianbonilla.domain.characters.repository.MarvelRepository
import com.cristhianbonilla.domain.characters.usecase.GetCharactersListUseCase
import com.cristhianbonilla.domain.characters.usecase.GetCharactersListUseCaseImpl
import com.cristhianbonilla.domain.mocks.characterListResultModelMock
import com.cristhianbonilla.support.config.GenericError
import com.cristhianbonilla.support.config.ResultDomain
import com.cristhianbonilla.support.config.UseCase
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

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetCharactersListUseCaseTest {

    @Rule
    @JvmField
    val testRule: TestRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val testCoroutineRule: TestCoroutineRule = TestCoroutineRule()

    @Mock
    lateinit var repository: MarvelRepository

    lateinit var getCharactersListUseCase: GetCharactersListUseCase

    @Before
    fun setUp() {
        getCharactersListUseCase = GetCharactersListUseCaseImpl(repository)
    }

    @Test
    fun `Get character list should return list on success`() = testCoroutineRule.runBlockingTest {

        // given
        whenever(repository.getCharacterList()).thenReturn(
            ResultDomain.Success(
                characterListResultModelMock
            )
        )

        // when
        val result = getCharactersListUseCase.invoke(UseCase.None)

        // then
        Assert.assertEquals(result.extractData?.data, result.extractData?.data)
    }

    @Test
    fun `Get character list should return list error`() = testCoroutineRule.runBlockingTest {

        // given
        whenever(repository.getCharacterList()).thenReturn(
            ResultDomain.Error(
                GenericError
            )
        )

        // when
        val result = getCharactersListUseCase.invoke(UseCase.None)

        // then
        Assert.assertTrue(result is ResultDomain.Error)
    }
}
