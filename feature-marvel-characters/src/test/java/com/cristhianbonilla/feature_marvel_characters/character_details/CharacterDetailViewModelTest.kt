package com.cristhianbonilla.feature_marvel_characters.character_details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.cristhianbonilla.domain.characters.usecase.GetCharacterByIdUseCase
import com.cristhianbonilla.feature_marvel_characters.TestCoroutineRule
import com.cristhianbonilla.feature_marvel_characters.mocks.characterDetailResponseModel
import com.cristhianbonilla.support.config.GenericError
import com.cristhianbonilla.support.config.ResultDomain
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class CharacterDetailViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var getCharacterByIdUseCase: GetCharacterByIdUseCase

    private lateinit var viewModel: CharacterDetailViewModel

    @Mock
    private lateinit var stateObserver: Observer<CharacterDetailState>

    @Before
    fun setUp() {
        viewModel = CharacterDetailViewModel(getCharacterByIdUseCase)
        viewModel.state.observeForever(stateObserver)
    }

    @Test
    fun `Get Character Details from service error`(): Unit = testCoroutineRule.runBlockingTest {
        val characterId = "1"
        // given
        val expectedResult = ResultDomain.Error(GenericError)
        Mockito.`when`(getCharacterByIdUseCase.invoke(GetCharacterByIdUseCase.Params(characterId)))
            .thenReturn(expectedResult)

        // when
        viewModel.getCharacterDetailById(characterId)

        // then
        Mockito.verify(stateObserver).onChanged(CharacterDetailState.Loading)
        Mockito.verify(getCharacterByIdUseCase, Mockito.times(1))
            .invoke(GetCharacterByIdUseCase.Params(characterId))
        Mockito.verify(stateObserver).onChanged(CharacterDetailState.Error)
        Mockito.verifyNoMoreInteractions(getCharacterByIdUseCase)
    }

    @Test
    fun `Get Character Details from service success`(): Unit = testCoroutineRule.runBlockingTest {
        val characterId = "1"
        // given
        val expectedResult = ResultDomain.Success(characterDetailResponseModel)

        Mockito.`when`(getCharacterByIdUseCase.invoke(GetCharacterByIdUseCase.Params(characterId)))
            .thenReturn(expectedResult)

        // when
        viewModel.getCharacterDetailById(characterId)

        // then
        Mockito.verify(stateObserver).onChanged(CharacterDetailState.Loading)
        Mockito.verify(getCharacterByIdUseCase, Mockito.times(1)).invoke(GetCharacterByIdUseCase.Params(characterId))
        Mockito.verify(stateObserver).onChanged(
            CharacterDetailState.ShowCharacterDetail(characterDetailResponseModel.characterDetail.results)
        )
        Mockito.verifyNoMoreInteractions(getCharacterByIdUseCase)
    }
}
