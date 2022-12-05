package com.cristhianbonilla.feature_marvel_characters.character_list

import com.cristhianbonilla.domain.characters.model.list.CharacterModel

sealed class MarvelCharacterListState {
    object Loading : MarvelCharacterListState()
    object Error : MarvelCharacterListState()
    data class ShowMarvelCharacterList(val characterList: List<CharacterModel>?) :
        MarvelCharacterListState()
}
