package com.example.project2dam.fragments

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project2dam.ActivityAddCar
import com.example.project2dam.ActivityInicioClient
import com.example.project2dam.R
import com.example.project2dam.models.Coche
import com.example.project2dam.recycler.AdapterCar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_car.*
import kotlinx.android.synthetic.main.register_car.*


class CarFragment : Fragment() {

    private val db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_car, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val coches: ArrayList<Coche> = ArrayList<Coche>()

        var adapter: AdapterCar = AdapterCar(this, coches)

        obtCars()

        btnIrRegistroCoche.setOnClickListener{
            val cambiarPantalla = Intent(context, ActivityAddCar::class.java)
            startActivity(cambiarPantalla)
        }

        recyclerListaCoches.adapter=adapter
        recyclerListaCoches.layoutManager=LinearLayoutManager(context)

    }

     fun obtCars(){
        val user = Firebase.auth.currentUser?.email
        val marca:String?=null
        val modelo:String?=null
        val matricula:String?=null
        val ano:String?=null
        val color:String?=null

        db.collection("users").get().addOnSuccessListener {
                    documents  ->

                for (document in documents ) {
                    Toast.makeText(context, "${document.id} => ${document.data}", Toast.LENGTH_LONG).show()
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }


    }

}