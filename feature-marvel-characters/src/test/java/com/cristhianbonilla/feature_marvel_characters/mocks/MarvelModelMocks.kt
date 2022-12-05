package com.cristhianbonilla.feature_marvel_characters.mocks

import com.cristhianbonilla.domain.characters.model.detail.CharacterDetailModel
import com.cristhianbonilla.domain.characters.model.detail.CharacterDetailResponseModel
import com.cristhianbonilla.domain.characters.model.list.CharacterListResultModel
import com.cristhianbonilla.domain.characters.model.list.CharacterModel
import com.cristhianbonilla.domain.characters.model.list.CharacterResponseModel
import com.cristhianbonilla.domain.characters.model.list.CharacterThumbnailModel
import com.cristhianbonilla.domain.characters.model.list.CharacterUrlModel

internal val characterListResultModelMock = CharacterResponseModel(
    CharacterListResultModel(
        listOf(
            CharacterModel(
                "1",
                "true",
                "Thor",
                "url",
                CharacterThumbnailModel("jpg", "https://image"),
                listOf(CharacterUrlModel("", ""))
            )
        ),
        "1"
    )
)

internal val characterDetailResponseModel = CharacterDetailResponseModel(
    CharacterDetailModel(
        listOf(
            com.cristhianbonilla.domain.characters.model.detail.CharacterModel(
                "1",
                "true",
                "Thor",
                "url",
                com.cristhianbonilla.domain.characters.model.detail.CharacterThumbnailModel(
                    "jpg",
                    "https://image"
                ),
            )
        )
    )
)
