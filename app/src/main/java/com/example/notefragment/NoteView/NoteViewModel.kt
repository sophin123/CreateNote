package com.example.notefragment.NoteView

import android.util.Log
import androidx.lifecycle.ViewModel

class NoteViewModel : ViewModel(){
    var wordText = ""


    override fun onCleared(){
        Log.i("NoteFragment", "Note View Fragment Cleared")
    }
}