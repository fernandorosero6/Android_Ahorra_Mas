package com.example.ahorra

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RegistroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide() // Oculta la ActionBar de forma segura si no es nula
        enableEdgeToEdge() // Habilita el borde a borde si estás usando AndroidX Activity
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro)

        val InicioButton: Button = findViewById(R.id.Btn_Crear)

        // Establece un OnClickListener para el botón
        InicioButton.setOnClickListener {
            // Inicia la RegisterActivity
            val intent = Intent(this, InicioActivity::class.java)
            startActivity(intent)
        }


    }
}