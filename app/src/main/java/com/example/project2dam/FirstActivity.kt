package com.example.project2dam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class FirstActivity : AppCompatActivity() {
    private var btnIniciaFace: Button? = null
    private var btnIniciaSesion: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun iniciaSesion(view: View?){
       val  btnIniciaSesion: Button =view as Button
        val cambiarPantalla = Intent(this, LoginActivity::class.java)
        this.startActivity(cambiarPantalla)
    }
    fun noDisponibleAun(view: View?) {
        val toast = Toast.makeText(applicationContext, "Función no disponible aún! :(", Toast.LENGTH_SHORT)
        toast.show()
    }
}