package com.example.notefragment.NoteList

import android.util.Log
import androidx.lifecycle.ViewModel

class NoteListViewModel(finalText: String) : ViewModel() {
    var wordtext = finalText

    init {
        Log.i("Final Text", "Final Text is $finalText")
    }
}