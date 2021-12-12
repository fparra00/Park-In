package com.example.project2dam.recycler

import android.view.ViewGroup
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
    }

    override fun getItemCount(): Int {
        return coches.size
    }

}