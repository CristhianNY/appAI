package com.cristhianbonilla.feature_home.ui.sermon_transcription

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SermonTranscriptionViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Sermon Transcription Fragment"
    }
    val text: LiveData<String> = _text
}
