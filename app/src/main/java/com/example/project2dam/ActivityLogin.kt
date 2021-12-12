package com.example.project2dam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_owner.*

class ActivityLogin : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setup();
    }

    fun pantallaInicio(view: View?) {
        val cambiarPantalla = Intent(this, ActivityInicio::class.java)
        startActivity(cambiarPantalla)
    }

    private fun setup() {
        btnIniciaSesion.setOnClickListener {
            if (txtContrasena.text.isNotEmpty() && txtLogin.text.isNotEmpty()) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(txtLogin.text.toString(), txtContrasena.text.toString()).addOnCompleteListener {

                    Toast.makeText(this,it.toString(), Toast.LENGTH_LONG).show()

                    if(it.isSuccessful){

                        showHome(it.result?.user?.email ?: "", ActivityInicio.ProviderType.BASIC)
                    } else {
                        showAlert()
                    }
                }
            }
        }

    }

    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showHome(email:String, provider: ActivityInicio.ProviderType){
        val homeIntent = Intent(this, ActivityInicio::class.java).apply {
            putExtra("email", txtLogin.text)
            putExtra("provider",provider.name
            )
        }
        startActivity(homeIntent)
    }

}