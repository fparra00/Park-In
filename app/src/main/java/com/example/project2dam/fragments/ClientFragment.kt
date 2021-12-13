package com.example.project2dam.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.project2dam.ActivityLogin
import com.example.project2dam.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
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

        //Funcion on click del boton de registro
        btnRegistrarClient.setOnClickListener {
            if (compForm()) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    txtCorreo.text.toString(),
                    txtPass.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        if (saveBdd()) {
                            goToLoginView(txtCorreo.text.toString())
                        }
                    } else {
                        showAlert(R.string.errorRegistro)
                    }
                }
            }
        }


    }

    /*
    Funcion para crear una coleccion en la bdd con un documento por usuario, registrando los campos nombre, telefono, email y contraseña
     */
    private fun saveBdd(): Boolean {
        db.collection("users").document(txtCorreo.text.toString()).set(
            hashMapOf(
                "Name" to txtModelo.text.toString(),
                "Phone" to spinCl.selectedItem.toString()
                        + " " + txtTelefono.text.toString(),
                "Email" to txtCorreo.text.toString(),
                "Password" to txtConfirmPass.text.toString(),
                "Bussines" to false,
                "Empresa" to null,
            )
        )
        return true
    }

    /*
    Funcion para lanzar alert en caso de fallo registrando al usuario
     */
    private fun showAlert(msj: Int) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Error")
        builder.setMessage(msj)
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    /*
    Funcion para comprobar los campos de nuestro formulario antes de crear el usuario
     */
    private fun compForm(): Boolean {
        if (txtPass.text.toString() != txtConfirmPass.text.toString()) {
            showAlert(R.string.errorContrasenas)
            Toast.makeText(
                context,
                txtPass.text.toString() + "" + txtConfirmPass.text.toString(),
                Toast.LENGTH_LONG
            ).show()
            return false
        }
        if (txtModelo.text.isEmpty() || txtCorreo.text.isEmpty() || txtTelefono.text.isEmpty() || txtPass.text.isEmpty() || txtConfirmPass.text.isEmpty()) {
            showAlert(R.string.errorCampos)
            return false
        }
        if (!checkBox2.isChecked) {
            showAlert(R.string.errorTerminos)
            return false
        }
        return true
    }

    /*
    Funcion que nos retorna al login, en caso de que el usuario se haya registrado correctamente, mandando por bundle el correo
     */
    private fun goToLoginView(email: String) {
        val bundle = Bundle()
        val intent = Intent(requireContext(), ActivityLogin::class.java).apply {
            bundle.putString("email", txtCorreo.text.toString())
        }
        intent.putExtras(bundle)
        startActivity(intent)
    }
}