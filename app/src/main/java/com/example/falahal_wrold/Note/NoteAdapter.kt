package com.example.falahal_wrold.Note

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.example.falahal_wrold.Home.pertemuan9.ListFragment
import com.example.falahal_wrold.data.entity.NoteEntity
import com.example.falahal_wrold.databinding.ItemNoteBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NoteAdapter(
    private val notes: List<NoteEntity>,
    private val listFragment: ListFragment
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    class NoteViewHolder(val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.binding.tvTitle.text = note.title
        holder.binding.tvContent.text = note.content
        
        val date = Date(note.createdAt)
        val formatter = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault())
        holder.binding.tvDate.text = formatter.format(date)

        if (!note.imageUri.isNullOrEmpty()) {
            holder.binding.cardImage.visibility = View.VISIBLE
            Glide.with(holder.itemView.context)
                .load(note.imageUri)
                .centerCrop()
                .into(holder.binding.ivNoteImage)
        } else {
            holder.binding.cardImage.visibility = View.GONE
        }

        holder.itemView.setOnClickListener {
            Snackbar.make(holder.itemView, "Catatan: ${note.title}", Snackbar.LENGTH_SHORT).show()
        }

        holder.binding.btnDelete.setOnClickListener {
            MaterialAlertDialogBuilder(holder.itemView.context)
                .setTitle("Hapus Catatan")
                .setMessage("Apakah kamu yakin ingin menghapus catatan ini?")
                .setPositiveButton("Ya") { dialog, _ ->
                    listFragment.deleteNote(note)
                    dialog.dismiss()
                }
                .setNegativeButton("Batal") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }

    override fun getItemCount(): Int = notes.size
}