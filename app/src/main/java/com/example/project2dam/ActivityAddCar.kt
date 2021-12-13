package com.example.project2dam

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.project2dam.fragments.CarFragment
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

    private val carFragment = CarFragment()
    private val db = FirebaseFirestore.getInstance()
    private var colorCar:String? = null


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
                        colorCar = selectedColor.toString()
                    })
                .show(supportFragmentManager)
        }

        btnRegisterCar.setOnClickListener {

            createCar()

            val bundle = Bundle()
            val intent = Intent(this, ActivityInicioClient::class.java).apply {
                bundle.putInt("car", 1)
            }
            intent.putExtras(bundle)
            startActivity(intent)

        }
    }

    /*
    Funcion para crear un coche e introducirlo en BDD
     */
    public fun createCar(){
        val user = Firebase.auth.currentUser?.email


        val messageRef = db
            .collection("users").document(user.toString())
            .collection("cars").document("models")

            messageRef.set(
                hashMapOf(
                    "Brand" to spMarcas.selectedItem,
                    "Model" to txtModeloCar.text.toString(),
                    "Matricula" to txtMatricula.text.toString(),
                    "Date" to txtDateCar.text.toString(),
                    "Color" to colorCar
                )
            )
    }


}



