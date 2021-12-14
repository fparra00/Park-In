package com.example.project2dam.fragments

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.project2dam.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapFragment : Fragment(), OnMapReadyCallback {

    //Variables auxiliares
    private val PETICION_PERMISOS_GPS = 1
    private var coordinates: Location = Location("location")
    private lateinit var mMap: GoogleMap


    /**
     * Funcion on create que instancia el fragmento lista de mapa
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Infla el layout de este fragmento
        return inflater.inflate(R.layout.fragment_map, container, false)


    }

    /**
     * Funcion que inicializa el fragment
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapF = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapF?.getMapAsync(this)
    }

    /**
     * Funcion que se ejecuta al inicializarse el mapa
     */
    override fun onMapReady(p0: GoogleMap) {
        if ((ActivityCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED) &&
            (ActivityCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED)
        ) {

            val locationManager: LocationManager =
                requireContext().getSystemService(AppCompatActivity.LOCATION_SERVICE) as LocationManager
            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

                val cenec = LatLng(36.7114278293636, -4.435965885987433)
                p0.addMarker(
                    MarkerOptions()
                        .position(cenec)
                        .title("CENEC Malaga")
                )
                p0.moveCamera(CameraUpdateFactory.newLatLngZoom(cenec, 16.0f))
            } else if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {

                val cenec = LatLng(36.7114278293636, -4.435965885987433)
                p0.addMarker(
                    MarkerOptions()
                        .position(cenec)
                        .title("CENEC Malaga")
                )
                p0.moveCamera(CameraUpdateFactory.newLatLngZoom(cenec, 16.0f))
            } else {
                Toast.makeText(requireContext(), "0 idea de donde estas", Toast.LENGTH_SHORT).show()
            }
        } else {
            if (!(ActivityCompat.shouldShowRequestPermissionRationale(
                    requireActivity(),
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ) && ActivityCompat.shouldShowRequestPermissionRationale(
                    requireActivity(),
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ))
            ) {
                Toast.makeText(
                    requireContext(),
                    "No tengo permisos para acceder al gps",
                    Toast.LENGTH_SHORT
                ).show()
            }

            //Pido el permiso
            ActivityCompat.requestPermissions(
                requireActivity(), arrayOf<String>(
                    android.Manifest.permission.ACCESS_COARSE_LOCATION,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ), PETICION_PERMISOS_GPS
            )
        }

    }


    /**
     * Funciones asincronas que devuelven distintos datos sobre la localizacion
     */
    private val locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
    }


    /**
     * Funcion que se ejecuta cuando el usuario no ha aceptado permisos aún o los ha revocado
     */
    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == this.PETICION_PERMISOS_GPS) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val locationManager: LocationManager =
                    requireContext().getSystemService(AppCompatActivity.LOCATION_SERVICE) as LocationManager
                if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    locationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        0L,
                        0f,
                        locationListener
                    )
                } else if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                    locationManager.requestLocationUpdates(
                        LocationManager.NETWORK_PROVIDER,
                        0L,
                        0f,
                        locationListener
                    )
                } else {
                    Toast.makeText(requireContext(), "0 idea de donde estas", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                Toast.makeText(
                    requireContext(),
                    "No tengo permisos para llamar",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

}


