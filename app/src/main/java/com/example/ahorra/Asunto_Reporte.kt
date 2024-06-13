package com.example.ahorra

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.ahorra.databinding.ActivityAsuntoReporteBinding

class Asunto_Reporte : AppCompatActivity() {
    private lateinit var binding: ActivityAsuntoReporteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAsuntoReporteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.root.setOnApplyWindowInsetsListener { view, insets ->
            insets
        }
    }
}
