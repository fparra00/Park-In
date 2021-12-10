package com.example.project2dam

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.project2dam.fragments.*
import kotlinx.android.synthetic.main.activity_inicio.*
import kotlinx.android.synthetic.main.activity_register.*

class ActivityRegister : AppCompatActivity(){

    private val clientFragment = ClientFragment()
    private val ownerFragment = OwnerFragment()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        replaceFragment(clientFragment)

        top_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.client -> replaceFragment(clientFragment)
                R.id.owner -> replaceFragment(ownerFragment)
            }
            true
        }

    }


    private fun replaceFragment(fragment: Fragment){
        if (fragment!=null){
            val transaction= supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainer, fragment)
            transaction.commit()

        }

    }
}