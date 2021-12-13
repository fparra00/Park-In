package com.example.project2dam

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dev.sasikanth.colorsheet.ColorSheet
import kotlinx.android.synthetic.main.register_car.*

class ActivityAddCar : AppCompatActivity() {

    //Aux Picker Color
    companion object {
        private const val COLOR_SELECTED = "selectedColor"
        private const val NO_COLOR_OPTION = "noColorOption"
    }



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
                    })
                .show(supportFragmentManager)
        }
    }


}



