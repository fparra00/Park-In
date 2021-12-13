package com.example.project2dam.recycler

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project2dam.R

class GarageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val garage: TextView by lazy { itemView.findViewById(R.id.txtGarage) }

}