package com.example.project2dam.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project2dam.R
import com.example.project2dam.models.Coche
import com.example.project2dam.models.Garage
import com.example.project2dam.recycler.AdapterCar
import com.example.project2dam.recycler.AdapterGarage
import kotlinx.android.synthetic.main.fragment_car.*
import kotlinx.android.synthetic.main.fragment_car.recyclerListaCoches
import kotlinx.android.synthetic.main.fragment_list_garage.*

class ListGarageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_garage, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val garages: ArrayList<Garage> = ArrayList<Garage>()

        var adapter: AdapterGarage = AdapterGarage(this, garages)

        recyclerListaGarages.adapter=adapter
        recyclerListaGarages.layoutManager= LinearLayoutManager(context)

    }

}