package com.maruf.characterlistapp.ui.interfaces

import com.maruf.characterlistapp.model.CharacterModelItem

interface SelectedCharacterDetails {
    fun selectedCharacter(character:CharacterModelItem)
}