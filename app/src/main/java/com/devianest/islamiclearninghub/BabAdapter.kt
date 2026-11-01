package com.devianest.islamiclearninghub // Sesuaikan nama paketnya

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BabAdapter(
    private val listBab: List<Bab>,
    private val onItemClick: (Bab) -> Unit
) : RecyclerView.Adapter<BabAdapter.BabViewHolder>() {

    class BabViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvJudul: TextView = itemView.findViewById(R.id.tvJudulBab)
        val tvDesc: TextView = itemView.findViewById(R.id.tvDescBab)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BabViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_bab, parent, false)
        return BabViewHolder(view)
    }

    override fun onBindViewHolder(holder: BabViewHolder, position: Int) {
        val bab = listBab[position]
        holder.tvJudul.text = bab.judul
        holder.tvDesc.text = bab.deskripsi

        holder.itemView.setOnClickListener {
            onItemClick(bab)
        }
    }

    override fun getItemCount(): Int = listBab.size
}