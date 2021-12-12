package com.example.project2dam

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.project2dam.fragments.CarFragment
import com.example.project2dam.fragments.MapFragment
import com.example.project2dam.fragments.SettingsFragment
import kotlinx.android.synthetic.main.activity_inicio_client.*

class ActivityInicioClient : AppCompatActivity() {

    private val mapFragment = MapFragment()
    private val settingsFragment = SettingsFragment()
    private val carFragment= CarFragment()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_client)
        replaceFragment(mapFragment)


        bottom_navigation_client.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.ic_map -> replaceFragment(mapFragment)
                R.id.ic_settings -> replaceFragment(settingsFragment)
                R.id.ic_car -> replaceFragment(carFragment)
            }
            true
        }

    }


    private fun replaceFragment(fragment: Fragment){
        if (fragment!=null){
            val transaction= supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainerClient, fragment)
            transaction.commit()

        }

    }

}