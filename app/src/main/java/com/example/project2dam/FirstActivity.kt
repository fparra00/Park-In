package com.example.project2dam

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.project2dam.fragments.CarFragment
import com.example.project2dam.fragments.MapFragment
import com.example.project2dam.fragments.SettingsFragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_main.*

class FirstActivity : AppCompatActivity() {
    private var btnIniciaFace: Button? = null
    private var btnIniciaSesion: Button? = null
    private var textView: TextView? = null
    private var default_id:String = "87382517071-dpil99tf34ufdjvkfip6ium6k84d0hqj.apps.googleusercontent.com"
    private val GOOGLE_SIGN_IN=100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //onClick Registro
        hrefCrearCuenta.setOnClickListener {
            val cambiarPantalla = Intent(this, ActivityRegister::class.java)
            startActivity(cambiarPantalla)
        }

        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)

        btnGoogle.setOnClickListener {
            val googleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(default_id)
                .requestEmail()
                .build()

            val googleClient = GoogleSignIn.getClient(this, googleConf)
            startActivityForResult(googleClient.signInIntent, GOOGLE_SIGN_IN)
        }
    }


    fun pantallaLogin(view: View?) {
        val cambiarPantalla = Intent(this, ActivityLogin::class.java)
        startActivity(cambiarPantalla)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == GOOGLE_SIGN_IN){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val account = task.getResult(ApiException::class.java)
            if(account != null){
                val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                FirebaseAuth.getInstance().signInWithCredential(credential)
                val cambiarPantalla = Intent(this, ActivityInicioClient::class.java)
                startActivity(cambiarPantalla)
            }
        }
    }


}