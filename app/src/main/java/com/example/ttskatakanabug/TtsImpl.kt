package com.example.ttskatakanabug

import android.content.Context
import android.speech.tts.TextToSpeech
import android.util.Log

class TtsImpl(context: Context) : TextToSpeech.OnInitListener {
    private val tts: TextToSpeech
    private var isTtsInitialized = false

    init {
        tts = TextToSpeech(context, this)
    }

    fun speak(text: String) {
        if (!isTtsInitialized) return
        tts.voices.firstOrNull { it.name.startsWith("ja") }?.let {
            tts.voice = it
        }
        tts.setSpeechRate(1f)
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    override fun onInit(status: Int) {
        Log.d(TAG, "onInit: $status")
        if (status == TextToSpeech.SUCCESS) {
            isTtsInitialized = true
        }
    }

    companion object {
        const val TAG = "TtsImpl"
    }
}
