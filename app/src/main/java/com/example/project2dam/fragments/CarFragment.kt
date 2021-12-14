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
    //Variables auxiliares
    private val db = FirebaseFirestore.getInstance()
    private lateinit var carArrayList: ArrayList<Coche>

    /**
    *Funcion on create que instancia el fragmento car
    */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Infla el layout en este fragmento
        return inflater.inflate(R.layout.fragment_car, container, false)
    }

    /**
     * Funcion que inicializa el fragment
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        obtCars()

        //Funcion onClick del boton registro coche
        btnIrRegistroCoche.setOnClickListener {
            val cambiarPantalla = Intent(context, ActivityAddCar::class.java)
            startActivity(cambiarPantalla)
        }


    }

    /**
     * Funcion que obtiene todos los garajes del usuario registrado y los añade a un recycler view
     */
    private fun obtCars() {
        val user = Firebase.auth.currentUser?.email


        carArrayList = arrayListOf<Coche>()
        val docRef = db.collection("users").document(user.toString())
            .collection("cars")
        docRef.get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    var car: Coche = Coche()
                    car.Brand = document.data.getValue("Brand").toString()
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