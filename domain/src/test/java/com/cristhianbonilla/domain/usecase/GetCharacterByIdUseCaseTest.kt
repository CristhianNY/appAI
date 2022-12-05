package com.cristhianbonilla.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.cristhianbonilla.domain.TestCoroutineRule
import com.cristhianbonilla.domain.characters.repository.MarvelRepository
import com.cristhianbonilla.domain.characters.usecase.GetCharacterByIdUseCase
import com.cristhianbonilla.domain.characters.usecase.GetCharacterByIdUseCaseImpl
import com.cristhianbonilla.domain.mocks.characterDetailResponseModel
import com.cristhianbonilla.support.config.GenericError
import com.cristhianbonilla.support.config.ResultDomain
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
class GetCharacterByIdUseCaseTest {

    @Rule
    @JvmField
    val testRule: TestRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val testCoroutineRule: TestCoroutineRule = TestCoroutineRule()

    @Mock
    lateinit var repository: MarvelRepository

    lateinit var getCharacterByIdUseCase: GetCharacterByIdUseCase

    @Before
    fun setUp() {
        getCharacterByIdUseCase = GetCharacterByIdUseCaseImpl(repository)
    }

    @Test
    fun `Get character details success`() = testCoroutineRule.runBlockingTest {

        val characterId = "1"
        // given
        whenever(repository.getCharacterById(characterId)).thenReturn(
            ResultDomain.Success(
                characterDetailResponseModel
            )
        )

        // when
        val result = getCharacterByIdUseCase.invoke(GetCharacterByIdUseCase.Params(characterId))

        // then
        Assert.assertEquals(
            result.extractData?.characterDetail,
            result.extractData?.characterDetail
        )
    }

    @Test
    fun `Get character Details error`() = testCoroutineRule.runBlockingTest {
        val characterId = "1"
        // given
        whenever(repository.getCharacterById(characterId)).thenReturn(
            ResultDomain.Error(
                GenericError
            )
        )

        // when
        val result = getCharacterByIdUseCase.invoke(GetCharacterByIdUseCase.Params(characterId))

        // then
        Assert.assertTrue(result is ResultDomain.Error)
    }
}
