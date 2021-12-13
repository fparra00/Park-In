package com.example.project2dam.recycler

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project2dam.R

class CarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val marcaCoche:TextView by lazy { itemView.findViewById(R.id.txtMarcaCoche) }
    val modeloCoche:TextView by lazy { itemView.findViewById(R.id.txtModeloCoche) }
    val matriculaCoche:TextView by lazy { itemView.findViewById(R.id.txtMatriculaCoche) }
    val icRemove:ImageView by lazy { itemView.findViewById(R.id.ic_removeGarage) }
}