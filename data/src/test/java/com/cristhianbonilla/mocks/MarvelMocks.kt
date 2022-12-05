package com.cristhianbonilla.mocks

import com.cristhianbonilla.data.characters.entity.detail.CharacterDetailEntity
import com.cristhianbonilla.data.characters.entity.detail.CharacterDetailResponseEntity
import com.cristhianbonilla.data.characters.entity.list.CharacterEntity
import com.cristhianbonilla.data.characters.entity.list.CharacterListResultEntity
import com.cristhianbonilla.data.characters.entity.list.CharacterResponseEntity
import com.cristhianbonilla.data.characters.entity.list.CharacterThumbnailEntity
import com.cristhianbonilla.data.characters.entity.list.CharacterUrlEntity

internal val characterListResultEntityMock = CharacterResponseEntity(
    CharacterListResultEntity(
        listOf(
            CharacterEntity(
                "1",
                "true",
                "Thor",
                "url",
                CharacterThumbnailEntity("jpg", "https://image"),
                listOf(CharacterUrlEntity("", ""))
            )
        ),
        "1"
    )
)

internal val characterDetailResponseEntity = CharacterDetailResponseEntity(
    CharacterDetailEntity(
        listOf(
            com.cristhianbonilla.data.characters.entity.detail.CharacterEntity(
                "1",
                "true",
                "Thor",
                "url",
                com.cristhianbonilla.data.characters.entity.detail.CharacterThumbnailEntity(
                    "jpg",
                    "https://image"
                ),
            )
        )
    )
)
