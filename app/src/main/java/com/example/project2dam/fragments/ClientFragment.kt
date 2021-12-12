package com.example.project2dam.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.project2dam.ActivityInicio
import com.example.project2dam.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_client.*


class ClientFragment : Fragment() {


    private val db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_client, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnRegistrarClient.setOnClickListener {
            if (txtPass.text.isNotEmpty() && txtCorreo.text.isNotEmpty()) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(txtCorreo.text.toString(), txtPass.text.toString()).addOnCompleteListener {
                    if(it.isSuccessful){
                        saveBdd()
                        showHome(it.result?.user?.email ?: "", ActivityInicio.ProviderType.BASIC)
                    } else {
                        showAlert()
                    }
                }
            }
        }
    }

    /*
    Funcion para crear 
     */
    private fun saveBdd(){
    db.collection("users").document(txtCorreo.text.toString()).set(
        hashMapOf("Name" to txtModelo.text.toString(),
        "Phone" to txtTelefono.text.toString(),
        "Email" to txtCorreo.text.toString(),
        "Password" to txtConfirmPass.text.toString())
    )
    }

    /*
    Funcion para lanzar alert en caso de fallo registrando al usuario
     */
    private fun showAlert(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error registrando al usuario, por favor, reinicie la app si el problema persiste")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showHome(email:String, provider: ActivityInicio.ProviderType){

    }
}