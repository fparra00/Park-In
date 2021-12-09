package com.example.project2dam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.project2dam.fragments.CarFragment
import com.example.project2dam.fragments.MapFragment
import com.example.project2dam.fragments.SettingsFragment
import kotlinx.android.synthetic.main.activity_inicio.*

class FirstActivity : AppCompatActivity() {
    private var btnIniciaFace: Button? = null
    private var btnIniciaSesion: Button? = null
    private var textView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    

    fun noDisponibleAun(view: View?) {
        val toast = Toast.makeText(applicationContext, "Función no disponible aún! :(", Toast.LENGTH_SHORT)
        toast.show()
    }

}