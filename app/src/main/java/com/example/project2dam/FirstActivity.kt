package com.example.project2dam

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_main.*

class FirstActivity : AppCompatActivity() {
    //Variables Auxiliares
    private var btnIniciaFace: Button? = null
    private var btnIniciaSesion: Button? = null
    private var textView: TextView? = null
    //Id Google necesario para la autenticacion
    private var default_id: String =
        "87382517071-dpil99tf34ufdjvkfip6ium6k84d0hqj.apps.googleusercontent.com"
    private val GOOGLE_SIGN_IN = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //onClick Registro
        hrefCrearCuenta.setOnClickListener {
            val cambiarPantalla = Intent(this, ActivityRegister::class.java)
            startActivity(cambiarPantalla)
        }

        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)

        //onClick btn 'Iniciar Sesion con Google'
        btnGoogle.setOnClickListener {
            val googleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(default_id)
                .requestEmail()
                .build()

            val googleClient = GoogleSignIn.getClient(this, googleConf)
            startActivityForResult(googleClient.signInIntent, GOOGLE_SIGN_IN)
        }
    }


    //onClick btn 'Inicia Sesion'
    fun pantallaLogin(view: View?) {
        val cambiarPantalla = Intent(this, ActivityLogin::class.java)
        startActivity(cambiarPantalla)
    }

    /*
    Sobreescritura del metodo onActivityResult
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //Recogemos el codigo, y en caso de pertenecer al de autenticacion mediante google
        if (requestCode == GOOGLE_SIGN_IN) {
            try {
                //Creamos la tarea y cuenta, y comprobamos que el usuario exista mediante la api de Google
                val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                val account = task.getResult(ApiException::class.java)

                if (account != null) {
                    val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                    FirebaseAuth.getInstance().signInWithCredential(credential)
                        .addOnCompleteListener {
                            //En caso de inicio de sesion exitoso, redireccionamos a la pantalla principal
                            if (it.isSuccessful) {
                                val cambiarPantalla = Intent(this, ActivityInicioClient::class.java)
                                startActivity(cambiarPantalla)
                            }
                        }
                }
            } catch (e: ApiException) {
                val cambiarPantalla = Intent(this, ActivityInicioClient::class.java)
                startActivity(cambiarPantalla)
            }

        }
    }
}