package com.example.project2dam.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.project2dam.R


class SettingsFragment : Fragment() {

    /**
     * Funcion on create que instancia el fragmento lista de ajustes
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Infla el layout en este fragmento
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }


}