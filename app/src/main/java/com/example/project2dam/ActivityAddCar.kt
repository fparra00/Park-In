package com.example.project2dam

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import dev.sasikanth.colorsheet.ColorSheet
import kotlinx.android.synthetic.main.register_car.*

class ActivityAddCar : AppCompatActivity() {

    //Aux Picker Color
    companion object {
        private const val COLOR_SELECTED = "selectedColor"
        private const val NO_COLOR_OPTION = "noColorOption"
    }

    private val db = FirebaseFirestore.getInstance()
    var carColor:String?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        var selectedColor: Int = ColorSheet.NO_COLOR
        var noColorOption = false
        val colors = resources.getIntArray(R.array.colors)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_car)

        //Funcion para Picker de Color
        selectedColor = savedInstanceState?.getInt(COLOR_SELECTED) ?: colors.first()
        noColorOption = savedInstanceState?.getBoolean(NO_COLOR_OPTION) ?: false

        //onClick de picker de color
        btnColor.setOnClickListener {
            ColorSheet().cornerRadius(8)
                .colorPicker(
                    colors = colors,
                    noColorOption = noColorOption,
                    selectedColor = selectedColor,
                    listener = { color ->
                        selectedColor = color
                        btnColor.setBackgroundColor(selectedColor)
                        carColor == selectedColor.toString()
                    })
                .show(supportFragmentManager)
        }

        //onClick registrarCoche
        btnRegisterCar.setOnClickListener {
            createCar()
        }
    }

     fun createCar() {
        //Obtenemos el email del usuario logueado actualmente
        val user = Firebase.auth.currentUser

        var email: String? = null
        user?.let {
            email = user.email.toString()

        }
        //Creacion de nuestro nueva coleccion y documento en firebase
        val messageRef = db
            .collection("users").document(email.toString())
            .collection("cars").document(txtMatricula.text.toString())

         messageRef.set(
            hashMapOf(
                "Brand" to spMarcas.selectedItem.toString(),
                "Model" to txtModeloCar.text.toString(),
                "Enrollment" to txtMatricula.text.toString(),
                "Date" to txtDateCar.text.toString(),
                "Color" to carColor.toString()
            )
        )
    }


}



