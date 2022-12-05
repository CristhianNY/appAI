package com.cristhianbonilla.feature_marvel_characters.character_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cristhianbonilla.domain.characters.usecase.GetCharactersListUseCase
import com.cristhianbonilla.feature_marvel_characters.character_list.MarvelCharacterListState.Error
import com.cristhianbonilla.feature_marvel_characters.character_list.MarvelCharacterListState.Loading
import com.cristhianbonilla.feature_marvel_characters.character_list.MarvelCharacterListState.ShowMarvelCharacterList
import com.cristhianbonilla.support.config.ResultDomain
import com.cristhianbonilla.support.config.UseCase
import com.cristhianbonilla.support.config.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MarvelCharacterListViewModel @Inject constructor(private val getCharactersListUseCase: GetCharactersListUseCase) :
    ViewModel() {

    private val _state = MutableLiveData<MarvelCharacterListState>()
    val state = _state.asLiveData()

    fun getCharacterList() {
        setState(Loading)
        viewModelScope.launch {
            when (
                val response = getCharactersListUseCase(UseCase.None)
            ) {
                is ResultDomain.Success -> setState(
                    ShowMarvelCharacterList(
                        response.data?.data?.characterEntities.orEmpty()
                    )
                )

                is ResultDomain.Error -> setState(Error)
            }
        }
    }

    private fun setState(state: MarvelCharacterListState?) {
        _state.value = state
    }
}
