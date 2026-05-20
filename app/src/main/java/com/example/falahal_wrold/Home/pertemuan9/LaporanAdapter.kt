package com.example.falahal_wrold.Home.pertemuan9

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.falahal_wrold.R

class LaporanAdapter(
    private val listLaporan: ArrayList<LaporanModel>
) : RecyclerView.Adapter<LaporanAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imgBencana: ImageView =
            itemView.findViewById(R.id.imgBencana)

        val tvJudul: TextView =
            itemView.findViewById(R.id.tvJudul)

        val tvDeskripsi: TextView =
            itemView.findViewById(R.id.tvDeskripsi)

        val tvLokasi: TextView =
            itemView.findViewById(R.id.tvLokasi)

        val tvStatus: TextView =
            itemView.findViewById(R.id.tvStatus)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_laporan,
                parent,
                false
            )

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {

        return listLaporan.size
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {

        val data = listLaporan[position]

        holder.tvJudul.text = data.judul

        holder.tvDeskripsi.text = data.deskripsi

        holder.tvLokasi.text = "Indonesia"

        holder.tvStatus.text = "SIAGA"

        Glide.with(holder.itemView.context)
            .load(data.imageUrl)
            .into(holder.imgBencana)
    }
}

// =========================
// MODEL DATA
// =========================

data class LaporanModel(

    val judul: String,
    val deskripsi: String,
    val imageUrl: String
)