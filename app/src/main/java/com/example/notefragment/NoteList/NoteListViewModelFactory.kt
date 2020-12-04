package com.example.notefragment.NoteList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class NoteListViewModelFactory(private var finalText: String): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteListViewModel::class.java)){
            return NoteListViewModel(finalText) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }

}