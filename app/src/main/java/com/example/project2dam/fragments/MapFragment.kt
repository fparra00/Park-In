package com.example.project2dam.fragments

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.project2dam.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.util.jar.Manifest

class MapFragment : Fragment(), OnMapReadyCallback {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false)

        //val mapFragment = supportFragmentManager.findFragmentById(R.layout.fragment_map) as? SupportMapFragment
       //mapFragment?.getMapAsync(this)
    }

    // [END maps_marker_get_map_async]
    // [END_EXCLUDE]

    // [START maps_marker_on_map_ready_add_marker]
    override fun onMapReady(googleMap: GoogleMap) {
        val sydney = LatLng(-33.852, 151.211)
        googleMap.addMarker(
            MarkerOptions()
                .position(sydney)
                .title("Marker in Sydney")
        )
        // [START_EXCLUDE silent]
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        // [END_EXCLUDE]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }



}
// [END maps_marker_on_map_ready]


