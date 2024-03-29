package com.example.project2dam.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.project2dam.ActivityLogin
import com.example.project2dam.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_owner.*

class OwnerFragment : Fragment() {

    //Variables auxiliares
    private val db = FirebaseFirestore.getInstance()


    /**
     * Funcion on create que instancia el fragmento lista de owner
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Infla el layout en este fragemnto
        return inflater.inflate(R.layout.fragment_owner, container, false)

    }

    /**
     * Funcion que inicializa el fragment
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Funcion on click del boton de registro
        btnRegistrarOwner.setOnClickListener {
            if (compForm()) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    txtCorreo2.text.toString(),
                    txtPass2.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        if (saveBdd()) {
                            goToLoginView(txtCorreo2.text.toString())
                        }
                    } else {
                        showAlert(R.string.errorRegistro)
                    }
                }
            }
        }
    }

    /**
     *Funcion para crear una coleccion en la bdd con un documento por usuario, registrando los campos nombre, telefono, email y contraseña
     */
    private fun saveBdd(): Boolean {
        db.collection("users").document(txtCorreo2.text.toString()).set(

            hashMapOf(
                "Empresa" to txtEmpresa2.text.toString(),
                "Name" to txtModelo2.text.toString(),
                "Phone" to spinnerOwner.selectedItem.toString()
                        + " " + txtTelefono2.text.toString(),
                "Email" to txtCorreo2.text.toString(),
                "Password" to txtPass2.text.toString(),
                "Bussines" to true
            )
        )
        return true
    }

    /**
     *Funcion para lanzar alert en caso de fallo registrando al usuario
     */
    private fun showAlert(msj: Int) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Error")
        builder.setMessage(msj)
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    /**
     *Funcion para comprobar los campos de nuestro formulario antes de crear el usuario
     */
    private fun compForm(): Boolean {
        if (txtPass2.text.toString() != txtConfirmPass2.text.toString()) {
            showAlert(R.string.errorContrasenas)

            return false
        }
        if (txtEmpresa2.text.isEmpty() || txtModelo2.text.isEmpty() || txtCorreo2.text.isEmpty() || txtTelefono2.text.isEmpty() || txtPass2.text.isEmpty() || txtConfirmPass2.text.isEmpty()) {
            showAlert(R.string.errorCampos)
            return false
        }
        if (!checkBox22.isChecked) {
            showAlert(R.string.errorTerminos)
            return false
        }
        return true
    }

    /**
     *Funcion que nos retorna al login, en caso de que el usuario se haya registrado correctamente, mandando por bundle el correo
     */
    private fun goToLoginView(email: String) {
        val bundle = Bundle()
        val intent = Intent(requireContext(), ActivityLogin::class.java).apply {
            bundle.putString("email", txtCorreo2.text.toString())
        }
        intent.putExtras(bundle)
        startActivity(intent)
    }

}