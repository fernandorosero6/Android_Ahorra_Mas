package com.example.ahorra

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ahorra.databinding.ActivityLoginBinding
import com.example.ahorra.ingreso.RecuperarContrasenaFragment1
import com.example.ahorra.singleton.RetrofitBroker
import com.example.crud.RegistroActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var edtCoD: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnRegist: Button
    private lateinit var btnContr: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Inicializa las vistas
        edtCoD = findViewById(R.id.EDT1)
        edtPassword = findViewById(R.id.EDT2)
        btnLogin = findViewById(R.id.Btn1_Login)
        btnRegist = findViewById(R.id.btnRegistro)
        btnContr = findViewById(R.id.btnContrasena)

        // Configura los listeners de los botones
        btnLogin.setOnClickListener {
            loginFunction()
        }

        btnRegist.setOnClickListener {
            registrarfunction()
        }

        btnContr.setOnClickListener {
            Contrasenafunction()
        }
    }

    private fun Contrasenafunction() {
        val binding: ActivityLoginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        val fragment = RecuperarContrasenaFragment1()
        fragmentTransaction.replace(R.id.main, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    private fun registrarfunction() {
        startActivity(Intent(this, RegistroActivity::class.java))
    }

    private fun loginFunction() {
        val email = edtCoD.text.toString().trim()
        val password = edtPassword.text.toString().trim()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor, ingresa tu correo y contraseña", Toast.LENGTH_SHORT).show()
            return
        }

        // Prepara los datos para Retrofit
       /* val credentials = mapOf("email" to email, "password" to password)

        RetrofitBroker.putRequest(credentials, { response ->
            // Maneja la respuesta exitosa
            Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, InicioActivity::class.java))
            finish()
        }, { error ->
            // Maneja el error
            Toast.makeText(this, "Error: $error", Toast.LENGTH_SHORT).show()
        })*/
    }
}
