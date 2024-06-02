package com.example.ahorra

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide() // Oculta la ActionBar de forma segura si no es nula
        enableEdgeToEdge() // Habilita el borde a borde si estás usando AndroidX Activity
        setContentView(R.layout.activity_login)
    }
}
