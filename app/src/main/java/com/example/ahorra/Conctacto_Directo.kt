package com.example.ahorra

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.ahorra.databinding.ActivityConctactoDirectoBinding

class Conctacto_Directo : AppCompatActivity() {
    private lateinit var binding: ActivityConctactoDirectoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Habilitar el modo edge-to-edge (asegúrate de tener esta función implementada)
        enableEdgeToEdge()

        // Inicializar ViewBinding correctamente
        binding = ActivityConctactoDirectoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar los insets si es necesario
        binding.root.setOnApplyWindowInsetsListener { view, insets ->
            // Manejar los insets aquí si es necesario
            insets
        }
    }
}
