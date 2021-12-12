package com.example.project2dam

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*

class ActivityLogin : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setup()
    }

    fun pantallaInicio(view: View?) {
        val cambiarPantalla = Intent(this, ActivityInicioClient::class.java)
        startActivity(cambiarPantalla)
    }

    /*
    Funcion para recuperar el bundle, en caso de que, el usuario se registre de manera exitosa, se mostrara su correo y solo tendrá que introducir
    la contraseña
     */
    fun setup() {
        val bundle = intent.extras
        val nom: String? = bundle?.getString("email")
        if (nom != null) {
            txtLogin.setText(nom)
        }
    }


}