package com.example.falahal_wrold.Home.photo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.falahal_wrold.R
import com.example.falahal_wrold.data.model.PhotoModel
import com.example.falahal_wrold.databinding.ItemPhotoBinding

class PhotoAdapter(private val items: List<PhotoModel>) : RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    inner class PhotoViewHolder(val binding: ItemPhotoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding = ItemPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val item = items[position]
        
        holder.binding.tvTitle.text = item.author 
        holder.binding.tvStatusBadge.text = "LAPORAN VISUAL"
        holder.binding.tvAuthor.text = "Sumber: Relawan Desa"
        holder.binding.tvDate.text = "Baru saja"
        
        Glide.with(holder.itemView.context)
            .load(item.download_url)
            .centerCrop()
            .placeholder(android.R.drawable.progress_horizontal)
            .error(android.R.drawable.ic_menu_report_image)
            .into(holder.binding.imgPhoto)
    }

    override fun getItemCount(): Int = items.size
}