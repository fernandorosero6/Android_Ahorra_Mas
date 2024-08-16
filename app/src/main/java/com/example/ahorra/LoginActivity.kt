package com.example.ahorra

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ahorra.databinding.ActivityLoginBinding
import com.example.ahorra.ingreso.RecuperarContrasenaFragment1

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
        // Usa ViewBinding o DataBinding si es necesario
        val binding: ActivityLoginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Maneja el fragmento
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        val fragment = RecuperarContrasenaFragment1()
        fragmentTransaction.replace(R.id.main, fragment) // Usa replace en lugar de add si quieres reemplazar el fragmento actual
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    private fun registrarfunction() {
        startActivity(Intent(this, RegistroActivity::class.java))
    }

    private fun loginFunction() {
        // Obtén los datos de los campos
        val email = edtCoD.text.toString().trim()
        val password = edtPassword.text.toString().trim()

        // Valida que los campos no estén vacíos
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor, ingresa tu correo y contraseña", Toast.LENGTH_SHORT).show()
            return
        }

        // Si los campos están completos, continúa con el proceso de inicio de sesión
        startActivity(Intent(this, InicioActivity::class.java))
        finish() // Cierra LoginActivity para evitar volver a ella con el botón "Atrás"
    }
}
