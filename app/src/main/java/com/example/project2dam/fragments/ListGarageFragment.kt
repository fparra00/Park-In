package com.example.project2dam.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project2dam.ActivityAddGarage
import com.example.project2dam.R
import com.example.project2dam.models.Garage
import com.example.project2dam.recycler.AdapterGarage
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_car.recyclerListaCoches
import kotlinx.android.synthetic.main.fragment_list_garage.*

class ListGarageFragment : Fragment() {

    private val db = FirebaseFirestore.getInstance()
    private lateinit var garageArrayList: ArrayList<Garage>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_garage, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        obtGarages()

        btnIrRegistroGaraje.setOnClickListener {
            val cambiarPantalla = Intent(context, ActivityAddGarage::class.java)
            startActivity(cambiarPantalla)
        }

    }
    private fun obtGarages() {
        val user = Firebase.auth.currentUser?.email


        garageArrayList = arrayListOf<Garage>()
        val docRef = db.collection("users").document(user.toString())
            .collection("garages")
        docRef.get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    var garage: Garage = Garage()
                    garage.Address = document.data.getValue("Address").toString()
                    garage.City = document.data.getValue("City").toString()
                    garage.nSlots = document.data.getValue("Number of Slots").toString()
                    garageArrayList.add(garage)
                }
                var adapter = AdapterGarage(context as FragmentActivity, garageArrayList)
                recyclerListaGarages.adapter = adapter
                recyclerListaGarages.layoutManager = LinearLayoutManager(context)
            }

    }

}