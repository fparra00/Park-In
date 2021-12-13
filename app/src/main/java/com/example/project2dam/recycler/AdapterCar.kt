package com.example.project2dam.recycler

import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.project2dam.R
import com.example.project2dam.fragments.CarFragment
import com.example.project2dam.models.Coche

class AdapterCar(val contexto: CarFragment, val coches:ArrayList<Coche>) : RecyclerView.Adapter<CarViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        return CarViewHolder(contexto.layoutInflater.inflate(R.layout.item_recycler_car, parent, false))
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.marcaCoche.text = coches.get(position).toString()
        holder.modeloCoche.text = coches.get(position).toString()
        holder.matriculaCoche.text = coches.get(position).toString()
        holder.icRemove.setOnClickListener {
            Toast.makeText(contexto.context, "Implementar funcion de borrar coches", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return coches.size
    }

}