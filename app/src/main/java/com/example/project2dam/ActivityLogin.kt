package com.example.project2dam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth

class ActivityLogin : AppCompatActivity() {

    public var txtUsu: EditText? = null
    public var txtPass: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


    }

    public fun pantallaInicio(view: View?) {
        val cambiarPantalla = Intent(this, ActivityInicioClient::class.java)
        startActivity(cambiarPantalla)
    }

    public fun login() {
        var pass = txtUsu?.findViewById<EditText>(R.id.txtContrasena)?.text.toString()
        var usu = txtUsu?.findViewById<EditText>(R.id.txtLogin)?.text.toString()

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(pass,usu).addOnCompleteListener{
            if(it.isSuccessful){
                Toast.makeText(this,"jeee",Toast.LENGTH_SHORT)
            } else {
                showAlert()
            }
        }

    }

    public fun showAlert(){

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Fallo Iniciando Sesión")
        builder.setMessage("Usuario o Contraseña Incorrectos!")
        builder.setPositiveButton("Aceptar",null)
        val dialog:AlertDialog = builder.create()
        dialog.show()

    }

}