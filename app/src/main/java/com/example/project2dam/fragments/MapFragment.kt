package com.example.project2dam.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import com.example.project2dam.ActivityInicioClient
import com.example.project2dam.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_map.*
import java.util.jar.Manifest

class MapFragment : Fragment(), OnMapReadyCallback {
    private val PETICION_PERMISOS_GPS = 1
    private var coordinates:Location = Location("location")
    //variables para location
    private lateinit var  mMap: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false)


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapF = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapF?.getMapAsync(this)


    }

    override fun onMapReady(p0: GoogleMap) {
        if ((ActivityCompat.checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) &&
            (ActivityCompat.checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ) ) {

            val locationManager:LocationManager = requireContext().getSystemService(AppCompatActivity.LOCATION_SERVICE) as LocationManager
            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                /*locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    0L,
                    0f,
                    locationListener

                )
                 */
                val malaga = LatLng(36.719444,-4.420000)
                p0.moveCamera(CameraUpdateFactory.newLatLngZoom(malaga, 14.0f))
            } else if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                locationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER,
                    0L,
                    0f,
                    locationListener
                )
                val malaga = LatLng(36.719444,-4.420000)
                p0.moveCamera(CameraUpdateFactory.newLatLng(malaga))
            } else {
                Toast.makeText(requireContext(),"0 idea de donde estas",Toast.LENGTH_SHORT).show()
            }
        } else {
            //Este segundo if es para comprobar si en caso de haber pedido los permisos y le ha dado a No volver a preguntar, mandarle un mensaje
            //Diciendo que el permiso no esta activado
            if (!(ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) && ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(),
                    android.Manifest.permission.ACCESS_FINE_LOCATION)) ) {
                Toast.makeText(requireContext(),"No tengo permisos para acceder al gps",Toast.LENGTH_SHORT).show()
            }

            //Pido el permiso
            ActivityCompat.requestPermissions(requireActivity(), arrayOf<String>(
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION),PETICION_PERMISOS_GPS)
        }

        //añadir marcador


    }




    fun permisoGPS() {
        //Compruebo si tiene el permiso, si es así procedo a llamar


    }
    private val locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
        }
        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
    }



    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == this.PETICION_PERMISOS_GPS) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val locationManager:LocationManager = requireContext().getSystemService(AppCompatActivity.LOCATION_SERVICE) as LocationManager
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
                    Toast.makeText(requireContext(),"0 idea de donde estas",Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(requireContext(),"No tengo permisos para llamar",Toast.LENGTH_SHORT).show()
            }
        }
    }

}


