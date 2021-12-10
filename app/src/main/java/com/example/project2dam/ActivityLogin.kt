package com.example.project2dam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth

class ActivityLogin : AppCompatActivity() {

    public var txtUsu: EditText? = null
    public var txtPass: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


    }

    public fun pantallaInicio(view: View?) {
        val cambiarPantalla = Intent(this, ActivityInicio::class.java)
        startActivity(cambiarPantalla)
    }

    public fun login() {
        var pass = txtUsu?.findViewById<EditText>(R.id.txtContrasena)?.text
        var usu = txtUsu?.findViewById<EditText>(R.id.txtLogin)?.text

        FirebaseAuth.getInstance().createUserWithEmailAndPassword()

    }

}