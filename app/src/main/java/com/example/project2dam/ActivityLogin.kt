package com.example.project2dam

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_login.*


class ActivityLogin : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //Funcion para llevar a cabo todas las acciones del login
        setup()
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
            if (compForm()) {
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

    /*
    Funcion para comprobar los campos del formulario
     */
    private fun compForm(): Boolean {
        if (txtLogin.text == null || txtLogin.text.length == 0 || txtContrasena.text == null || txtContrasena.text.length == 0) {
            showAlert(R.string.errLog)
            return false
        }
        return true
    }

    //Funcion para limpiar el formulario
    private fun clearForm() {
        txtLogin.setText("")
        txtContrasena.setText("")
    }

    //Funcion para cambiar a la pantalla de inicio
    fun pantallaInicio() {
        //Recuperamos si el usuario pertenece a un cliente o un owner, y mostramos el layout correspondiente
        db.collection("users").document(txtLogin.text.toString()).get().addOnSuccessListener {
            if (it.get("Bussines") as Boolean? == true) {
                val cambiarPantalla = Intent(this, ActivityInicioOwner::class.java)
                startActivity(cambiarPantalla)
            } else {
                val cambiarPantalla = Intent(this, ActivityInicioClient::class.java)
                startActivity(cambiarPantalla)
            }
        }
    }
}