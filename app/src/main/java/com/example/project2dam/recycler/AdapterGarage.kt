package com.example.project2dam.recycler

import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.project2dam.R
import com.example.project2dam.fragments.CarFragment
import com.example.project2dam.fragments.ListGarageFragment
import com.example.project2dam.models.Coche
import com.example.project2dam.models.Garage

class AdapterGarage(val contexto: ListGarageFragment, val garages:ArrayList<Garage>) : RecyclerView.Adapter<GarageViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GarageViewHolder {
        return GarageViewHolder(contexto.layoutInflater.inflate(R.layout.item_recycler_garage,parent,false))
    }

    override fun onBindViewHolder(holder: GarageViewHolder, position: Int) {
        holder.direccionGarage.text = garages.get(position).toString()
        holder.ciudadGarage.text = garages.get(position).toString()
        holder.numeroPlazas.text = garages.get(position).toString()
        holder.imgBorrarGarage.setOnClickListener{
            Toast.makeText(contexto.context, "Implementar funcion de borrar Garajes", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return garages.size
        }
}