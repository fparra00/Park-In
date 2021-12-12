package com.example.project2dam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
<<<<<<< Updated upstream
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.project2dam.fragments.CarFragment
import com.example.project2dam.fragments.MapFragment
import com.example.project2dam.fragments.SettingsFragment
=======
import kotlinx.android.synthetic.main.activity_main.*
>>>>>>> Stashed changes

class FirstActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    

    fun pantallaLogin(view: View?) {
        val cambiarPantalla = Intent(this, ActivityLogin::class.java)
        startActivity(cambiarPantalla)
    }

    fun pantallaRegistro(view: android.view.View) {
        val cambiarPantalla = Intent(this, ActivityRegister::class.java)
        startActivity(cambiarPantalla)
    }

}