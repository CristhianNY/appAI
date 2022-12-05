package com.cristhianbonilla.feature_marvel_characters.character_list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.cristhianbonilla.domain.characters.usecase.GetCharactersListUseCase
import com.cristhianbonilla.feature_marvel_characters.TestCoroutineRule
import com.cristhianbonilla.feature_marvel_characters.mocks.characterListResultModelMock
import com.cristhianbonilla.support.config.GenericError
import com.cristhianbonilla.support.config.ResultDomain
import com.cristhianbonilla.support.config.UseCase
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
class MarvelCharacterListViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var getCharactersListUseCase: GetCharactersListUseCase

    private lateinit var viewModel: MarvelCharacterListViewModel

    @Mock
    private lateinit var stateObserver: Observer<MarvelCharacterListState>

    @Before
    fun setUp() {
        viewModel = MarvelCharacterListViewModel(getCharactersListUseCase)
        viewModel.state.observeForever(stateObserver)
    }

    @Test
    fun `Get Character List from service error`(): Unit = testCoroutineRule.runBlockingTest {
        // given
        val expectedResult = ResultDomain.Error(GenericError)
        Mockito.`when`(getCharactersListUseCase.invoke(UseCase.None)).thenReturn(expectedResult)

        // when
        viewModel.getCharacterList()

        // then
        Mockito.verify(stateObserver).onChanged(MarvelCharacterListState.Loading)
        Mockito.verify(getCharactersListUseCase, Mockito.times(1)).invoke(UseCase.None)
        Mockito.verify(stateObserver).onChanged(MarvelCharacterListState.Error)
        Mockito.verifyNoMoreInteractions(getCharactersListUseCase)
    }

    @Test
    fun `Get Character List from service success`(): Unit = testCoroutineRule.runBlockingTest {
        // given
        val expectedResult = ResultDomain.Success(characterListResultModelMock)
        Mockito.`when`(getCharactersListUseCase.invoke(UseCase.None)).thenReturn(expectedResult)

        // when
        viewModel.getCharacterList()

        // then
        Mockito.verify(stateObserver).onChanged(MarvelCharacterListState.Loading)
        Mockito.verify(getCharactersListUseCase, Mockito.times(1)).invoke(UseCase.None)
        Mockito.verify(stateObserver).onChanged(MarvelCharacterListState.ShowMarvelCharacterList(characterListResultModelMock.data.characterEntities))
        Mockito.verifyNoMoreInteractions(getCharactersListUseCase)
    }
}
