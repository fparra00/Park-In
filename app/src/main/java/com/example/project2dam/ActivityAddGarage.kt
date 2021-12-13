package com.example.project2dam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add_garage.*
import kotlinx.android.synthetic.main.register_car.*

class ActivityAddGarage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_garage)

        btnRegisterGarage.setOnClickListener {

            val bundle = Bundle()
            val intent = Intent(this, ActivityInicioOwner::class.java).apply {
                bundle.putInt("garage", 1)
            }
            intent.putExtras(bundle)
            startActivity(intent)

        }
    }
}