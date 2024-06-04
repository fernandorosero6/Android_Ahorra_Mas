package com.example.ahorra

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.semantics.Role.Companion.Button

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide() // Oculta la ActionBar de forma segura si no es nula
        enableEdgeToEdge() // Habilita el borde a borde si estás usando AndroidX Activity
        setContentView(R.layout.activity_login)

        val registerTextView: TextView = findViewById(R.id.TXV4_Registrate)

        // Establece un OnClickListener para el botón
        registerTextView.setOnClickListener {
            // Inicia la RegisterActivity
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }
    }
}



