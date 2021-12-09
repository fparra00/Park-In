package com.example.project2dam

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegistroCoche: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registro_coche)
    }

    fun noDisponibleAun(view: View?) {
        val toast = Toast.makeText(applicationContext, "Función no disponible aún! :(", Toast.LENGTH_SHORT)
        toast.show()
    }
}