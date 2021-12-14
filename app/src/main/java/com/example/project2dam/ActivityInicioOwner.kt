package com.example.project2dam

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.project2dam.fragments.ListGarageFragment
import com.example.project2dam.fragments.MapFragment
import com.example.project2dam.fragments.SettingsFragment
import kotlinx.android.synthetic.main.activity_inicio_owner.*

class ActivityInicioOwner : AppCompatActivity() {

    private val mapFragment = MapFragment()
    private val settingsFragment = SettingsFragment()
    private val listGarageFragment = ListGarageFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_owner)
        val bundle = intent.extras
        if (bundle?.getInt("garage") == 1) {
            replaceFragment(listGarageFragment)
        } else {
            replaceFragment(mapFragment)
        }


        /*
        Reemplazo de fragmento en funcion del item clickado por el usuario
         */
        bottom_navigation_owner.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.ic_map -> replaceFragment(mapFragment)
                R.id.ic_settings -> replaceFragment(settingsFragment)
                R.id.ic_list -> replaceFragment(listGarageFragment)
            }
            true
        }

    }


    /*
    Logica utilizada para el reemplazo de fragmentos
     */
    private fun replaceFragment(fragment: Fragment) {
        if (fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainerOwner, fragment)
            transaction.commit()
        }

    }

}