package com.example.project2dam

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.btnIniciaSesion
import kotlinx.android.synthetic.main.activity_main.*
import com.google.android.gms.auth.api.signin.GoogleSignInAccount




class ActivityLogin : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun onStart() {
        super.onStart()
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


        //Funcion on click del boton de login
        btnIniciaSesion.setOnClickListener {
            //Comprobacion de Login
            FirebaseAuth.getInstance().signInWithEmailAndPassword(
                txtLogin.text.toString(),
                txtContrasena.text.toString()
            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    pantallaInicio()
                } else {
                    showAlert(R.string.errorInicio)
                    clearForm()
                }
            }
        }
    }


    /*
    Funcion para lanzar alert en caso de fallo registrando al usuario
    */
    private fun showAlert(msj: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Login Fallido")
        builder.setMessage(msj)
        builder.setPositiveButton("Reintentar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }


    //Funcion para limpiar el formulario
    private fun clearForm() {
        txtLogin.setText("")
        txtContrasena.setText("")
    }

    //Funcion para cambiar a la pantalla de inicio
    fun pantallaInicio() {
        val cambiarPantalla = Intent(this, ActivityInicioClient::class.java)
        startActivity(cambiarPantalla)
    }


}