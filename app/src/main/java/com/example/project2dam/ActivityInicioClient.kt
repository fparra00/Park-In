package com.example.project2dam

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.project2dam.fragments.CarFragment
import com.example.project2dam.fragments.MapFragment
import com.example.project2dam.fragments.SettingsFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_inicio_client.*
import kotlinx.android.synthetic.main.fragment_map.*

class ActivityInicioClient : AppCompatActivity(), OnMapReadyCallback {
    private val mapFragment = MapFragment()
    private val settingsFragment = SettingsFragment()
    private val carFragment = CarFragment()

    enum class ProviderType {
        BASIC,
        GOOGLE
    }


    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_client)
        val bundle = intent.extras
        if (bundle?.getInt("car") == 1) {
            replaceFragment(carFragment)
        } else {
            replaceFragment(mapFragment)
        }


        bottom_navigation_client.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.ic_map -> {
                    replaceFragment(mapFragment)
                }
                R.id.ic_settings -> replaceFragment(settingsFragment)
                R.id.ic_car -> replaceFragment(carFragment)
            }
            true
        }
    }


    private fun replaceFragment(fragment: Fragment) {
        if (fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainerClient, fragment)
            transaction.commit()

        }

    }

    override fun onMapReady(p0: GoogleMap) {
        Toast.makeText(this, "hahaha", Toast.LENGTH_SHORT).show()
        val sydney = LatLng(36.7, -4.474)
        p0.addMarker(
            MarkerOptions()
                .position(sydney)
                .title("Marker in Sydney")
        )

        p0.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

}