package com.example.project2dam

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import kotlinx.android.synthetic.main.activity_main.*

class FirstActivity : AppCompatActivity() {
    private var btnIniciaFace: Button? = null
    private var btnIniciaSesion: Button? = null
    private var textView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Guardado de datos
        val prefs =
            getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        prefs.apply()

        //onClick crearCuenta
        hrefCrearCuenta.setOnClickListener {
            val cambiarPantalla = Intent(this, ActivityRegister::class.java)
            startActivity(cambiarPantalla)
        }

        //onClick Google
        btnGoogle.setOnClickListener {
        val googleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id))
        }
    }

    fun pantallaLogin(view: View?) {
        val cambiarPantalla = Intent(this, ActivityLogin::class.java)
        startActivity(cambiarPantalla)
    }


}