package com.example.notefragment.NoteList

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notefragment.ModelClass
import com.example.notefragment.NoteAdapter
import com.example.notefragment.R


class NoteListFragment : Fragment(), NoteAdapter.OnItemClickListener {
    lateinit var recyclerView: RecyclerView
    lateinit var viewAdapter: RecyclerView.Adapter<*>
    lateinit var viewLayout: RecyclerView.LayoutManager
    val arrayList: ArrayList<ModelClass> = ArrayList()

    lateinit var noteListViewModel: NoteListViewModel
    lateinit var noteListViewModelFactory: NoteListViewModelFactory


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var root = inflater.inflate(R.layout.fragment_note_list, container, false)
        setHasOptionsMenu(true)

        Log.i("sethasOption Menu", "Called")
        return root
    }

    @SuppressLint("LongLogTag")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noteListViewModelFactory = NoteListViewModelFactory(NoteListFragmentArgs.fromBundle(requireArguments()).noteText)
        noteListViewModel = ViewModelProvider(this, noteListViewModelFactory).get(NoteListViewModel::class.java)
        var receiveText = noteListViewModel.wordtext



        arrayList.add(ModelClass("HeadLine 1"))
        arrayList.add(ModelClass(receiveText))



        recyclerView = view.findViewById(R.id.recyclerView)

        viewLayout = LinearLayoutManager(context)
        viewAdapter = NoteAdapter(arrayList, this)

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewLayout
            adapter = viewAdapter
        }

        //TouchHelperCallBack

        val itemTouchHelperCallBack = object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                if (direction == ItemTouchHelper.RIGHT){
                    (viewAdapter as NoteAdapter).removeItem(viewHolder)
                }
            }

        }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallBack)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.about_menu, menu)


        Log.i("Option Menu", "Called")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController()) || super.onOptionsItemSelected(item)
    }


    override fun onItemClicked(position: Int) {
        findNavController().navigate(R.id.action_noteLIstFragment_to_noteViewFragment)
    }

}