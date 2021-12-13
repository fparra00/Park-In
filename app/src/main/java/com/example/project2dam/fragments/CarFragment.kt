package com.example.project2dam.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project2dam.ActivityAddCar
import com.example.project2dam.ActivityInicioClient
import com.example.project2dam.R
import com.example.project2dam.models.Coche
import com.example.project2dam.recycler.AdapterCar
import kotlinx.android.synthetic.main.fragment_car.*


class CarFragment : Fragment() {

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

        btnIrRegistroCoche.setOnClickListener{
            val cambiarPantalla = Intent(context, ActivityAddCar::class.java)
            startActivity(cambiarPantalla)
        }

        recyclerListaCoches.adapter=adapter
        recyclerListaCoches.layoutManager=LinearLayoutManager(context)

    }





}