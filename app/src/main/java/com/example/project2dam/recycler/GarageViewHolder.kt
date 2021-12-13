package com.example.project2dam.recycler

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project2dam.R

class GarageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val direccionGarage:TextView by lazy { itemView.findViewById(R.id.txtDireccionGarage) }
    val ciudadGarage:TextView by lazy { itemView.findViewById(R.id.txtCiudad) }
    val numeroPlazas:TextView by lazy { itemView.findViewById(R.id.txtNplazas) }
    val imgBorrarGarage: ImageView by lazy { itemView.findViewById(R.id.ic_removeGarage) }

}