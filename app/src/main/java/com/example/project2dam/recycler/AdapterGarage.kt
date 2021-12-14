package com.example.project2dam.recycler

import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.project2dam.R
import com.example.project2dam.models.Garage
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class AdapterGarage(val contexto: FragmentActivity, val garages:ArrayList<Garage>) : RecyclerView.Adapter<GarageViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GarageViewHolder {
        return GarageViewHolder(contexto.layoutInflater.inflate(R.layout.item_recycler_garage,parent,false))
    }

    override fun onBindViewHolder(holder: GarageViewHolder, position: Int) {
        holder.direccionGarage.text = garages[position].Address.toString()+"  "
        holder.ciudadGarage.text = garages[position].City.toString()
        holder.numeroPlazas.text = garages[position].nSlots.toString()
        holder.imgBorrarGarage.setOnClickListener{
            FirebaseFirestore.getInstance().collection("users").document(Firebase.auth.currentUser?.email.toString())
                .collection("garages").document(garages[position].Address.toString()).delete()
            garages.removeAt(position)
        }
    }

    override fun getItemCount(): Int {
        return garages.size
        }
}