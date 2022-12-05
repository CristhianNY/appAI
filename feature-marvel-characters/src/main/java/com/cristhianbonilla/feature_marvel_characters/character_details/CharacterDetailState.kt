package com.cristhianbonilla.feature_marvel_characters.character_details

import com.cristhianbonilla.domain.characters.model.detail.CharacterModel

sealed class CharacterDetailState {
    object Loading : CharacterDetailState()
    object Error : CharacterDetailState()
    data class ShowCharacterDetail(val characterList: List<CharacterModel>?) :
        CharacterDetailState()
}
