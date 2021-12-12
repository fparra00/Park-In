package com.example.project2dam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class ActivityLogin : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


    }
    fun pantallaInicio(view: View?) {
        val cambiarPantalla = Intent(this, ActivityInicio::class.java)
        startActivity(cambiarPantalla)
    }


}