package com.example.notefragment.NoteView

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.notefragment.R


class NoteViewFragment : Fragment() {


    lateinit var viewModel: NoteViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_note_view, container, false)

        val sendBtn = root.findViewById<Button>(R.id.sendDataBtn)
        val receiveText = root.findViewById<EditText>(R.id.writtenEditText)


        viewModel = ViewModelProvider(this).get(NoteViewModel::class.java)



        sendBtn.setOnClickListener {
            viewModel.wordText = receiveText.text.toString()
            onReceiveText()

        }

        // Inflate the layout for this fragment
        return root
    }

    private fun onReceiveText(){

        val action = NoteViewFragmentDirections.actionNoteViewFragmentToNoteLIstFragment()
        action.noteText = viewModel.wordText

        Log.i("noteText", action.noteText)
        NavHostFragment.findNavController(this).navigate(action)
    }

  }

