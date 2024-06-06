package com.example.ahorra

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.semantics.Role.Companion.Button


class LoginActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
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

        val LoginButton: Button = findViewById(R.id.Btn1_Login)

        // Establece un OnClickListener para el botón

        LoginButton.setOnClickListener {
            // Inicia la RegisterActivity
            val intent = Intent(this, InicioActivity::class.java)
            startActivity(intent)

        }
    }
}



