package com.example.notefragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(private var arrayList: ArrayList<ModelClass>, private var onItemClickListener: OnItemClickListener): RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View, onItemClickListener: OnItemClickListener) : RecyclerView.ViewHolder(itemView){
        private var mnoteHeadLine: TextView = itemView.findViewById(R.id.itemNoteTextView)

        init {
            itemView.setOnClickListener {
                onItemClickListener.onItemClicked(adapterPosition)
            }
        }
        fun bind(modelClass: ModelClass){
            mnoteHeadLine.text = modelClass.noteHeadLine
        }

    }

    private var removePosition: Int = 0
    private var removeItem: String = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.note_view_layout, parent, false), onItemClickListener)
    }

    fun removeItem(viewHolder: RecyclerView.ViewHolder){
        removePosition = viewHolder.adapterPosition
        removeItem = arrayList[removePosition].toString()
        arrayList.removeAt(removePosition)
        notifyItemRemoved(removePosition)
    }

    override fun onBindViewHolder(holder: NoteAdapter.ViewHolder, position: Int) {
        holder.bind(arrayList[position])
    }

    override fun getItemCount(): Int {
       return arrayList.size
    }

    interface OnItemClickListener{
        fun onItemClicked(position: Int)
    }



}