package com.example.project2dam.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
<<<<<<< Updated upstream
=======
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.project2dam.ActivityInicioClient
>>>>>>> Stashed changes
import com.example.project2dam.R


class ClientFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_client, container, false)
    }

<<<<<<< Updated upstream
=======
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Funcion on click del boton de registro
        btnRegistrarClient.setOnClickListener {
            if (compForm()) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(txtCorreo.text.toString(), txtPass.text.toString()).addOnCompleteListener {
                    if(it.isSuccessful){
                        saveBdd()
                        showHome(it.result?.user?.email ?: "", ActivityInicioClient.ProviderType.BASIC)
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
    private fun showAlert(msj: Int){
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Error")
        builder.setMessage(msj)
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun compForm():Boolean{
        if(txtPass.text.toString() != txtConfirmPass.text.toString()){
            showAlert(R.string.errorContrasenas)
            Toast.makeText(context,txtPass.text.toString()+""+txtConfirmPass.text.toString(), Toast.LENGTH_LONG ).show()
            return false
        }
        if(txtModelo.text.isEmpty() || txtCorreo.text.isEmpty() || txtTelefono.text.isEmpty() ||  txtPass.text.isEmpty() || txtConfirmPass.text.isEmpty()){
            showAlert(R.string.errorCampos)
            return false
        }
        if(!checkBox2.isChecked){
            showAlert(R.string.errorTerminos)
            return false
        }
    return true
    }
    private fun showHome(email:String, provider: ActivityInicioClient.ProviderType){

    }
>>>>>>> Stashed changes
}