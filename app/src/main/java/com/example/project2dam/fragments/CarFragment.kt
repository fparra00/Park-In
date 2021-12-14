package com.example.project2dam.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project2dam.ActivityAddCar
import com.example.project2dam.R
import com.example.project2dam.models.Coche
import com.example.project2dam.recycler.AdapterCar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_car.*


class CarFragment : Fragment() {

    private val db = FirebaseFirestore.getInstance()
    private lateinit var carArrayList: ArrayList<Coche>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_car, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        obtCars()

        btnIrRegistroCoche.setOnClickListener {
            val cambiarPantalla = Intent(context, ActivityAddCar::class.java)
            startActivity(cambiarPantalla)
        }


    }

    private fun obtCars() {
        val user = Firebase.auth.currentUser?.email
        val marca: String? = null
        val modelo: String? = null
        val matricula: String? = null
        val ano: String? = null
        val color: String? = null


        carArrayList = arrayListOf<Coche>()
        val docRef = db.collection("users").document(user.toString())
            .collection("cars")
        docRef.get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    var car: Coche = Coche()
                    car.Brand = document.data.getValue("Brand").toString()
                    Toast.makeText(context, document.data.getValue("Brand").toString(), Toast.LENGTH_LONG).show()
                    car.Model = document.data.getValue("Model").toString()
                    car.Matricula = document.data.getValue("Matricula").toString()
                    carArrayList.add(car)
                }
                var adapter = AdapterCar(context as FragmentActivity, carArrayList)
                recyclerListaCoches.adapter = adapter
                recyclerListaCoches.layoutManager = LinearLayoutManager(context)
            }



    }

}