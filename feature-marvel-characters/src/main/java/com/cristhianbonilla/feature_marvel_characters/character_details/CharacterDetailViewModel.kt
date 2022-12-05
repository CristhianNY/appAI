package com.cristhianbonilla.feature_marvel_characters.character_details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cristhianbonilla.domain.characters.usecase.GetCharacterByIdUseCase
import com.cristhianbonilla.support.config.ResultDomain
import com.cristhianbonilla.support.config.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(private val getCharacterByIdUseCase: GetCharacterByIdUseCase) :
    ViewModel() {

    private val _state = MutableLiveData<CharacterDetailState>()
    val state = _state.asLiveData()

    fun getCharacterDetailById(characterId: String) {
        setState(CharacterDetailState.Loading)
        viewModelScope.launch {
            when (
                val response = getCharacterByIdUseCase(GetCharacterByIdUseCase.Params(characterId))
            ) {
                is ResultDomain.Success -> setState(
                    CharacterDetailState.ShowCharacterDetail(
                        response.data?.characterDetail?.results.orEmpty()
                    )
                )

                is ResultDomain.Error -> setState(CharacterDetailState.Error)
            }
        }
    }

    private fun setState(state: CharacterDetailState) {
        _state.value = state
    }
}
