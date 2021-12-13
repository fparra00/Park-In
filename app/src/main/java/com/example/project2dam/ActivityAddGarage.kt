package com.example.project2dam

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_add_garage.*
import kotlinx.android.synthetic.main.register_car.*

class ActivityAddGarage : AppCompatActivity() {

    //Var Aux
    private val db = FirebaseFirestore.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_garage)

        btnRegisterGarage.setOnClickListener {

            //Creamos un garaje y lo almacenamos en la bdd
            createGaraje()
            //Lo mandamos por Bundle
            val bundle = Bundle()
            val intent = Intent(this, ActivityInicioOwner::class.java).apply {
                bundle.putInt("garage", 1)
            }
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }

    /*
     Funcion para crear un coche e introducirlo en BDD
   */
    public fun createGaraje() {
        val user = Firebase.auth.currentUser?.email

        val messageRef = db
            .collection("users").document(user.toString())
            .collection("garages").document(txtDireccion.text.toString())

        messageRef.set(
            hashMapOf(
                "Address" to txtDireccion.text.toString(),
                "Number of Slots" to txtNumeroPlazas.text.toString(),
                "City" to txtCiudadGarage.text.toString(),
            )
        )
    }
}