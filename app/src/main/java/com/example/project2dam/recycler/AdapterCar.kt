package com.example.project2dam.recycler

import android.app.Activity
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.project2dam.R
import com.example.project2dam.fragments.CarFragment
import com.example.project2dam.models.Coche
import androidx.core.content.ContextCompat.startActivity

import android.content.Intent

import androidx.annotation.NonNull
import androidx.core.content.ContextCompat

import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase


class AdapterCar(val contexto: FragmentActivity, val coches:ArrayList<Coche>) : RecyclerView.Adapter<CarViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        return CarViewHolder(contexto.layoutInflater.inflate(R.layout.item_recycler_car, parent, false))
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.marcaCoche.text = coches[position].Brand.toString()+"   "
        holder.modeloCoche.text = coches[position].Model.toString()
        holder.matriculaCoche.text = coches[position].Matricula.toString()
        holder.icRemove.setOnClickListener {
            FirebaseFirestore.getInstance().collection("users").document(Firebase.auth.currentUser?.email.toString())
                .collection("cars").document(coches[position].Matricula.toString()).delete()
                coches.removeAt(position)
                this.notifyDataSetChanged()
            }
        }


    override fun getItemCount(): Int {
        return coches.size
    }

}