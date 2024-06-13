package com.example.ahorra

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.ahorra.databinding.ActivityPerfilBinding

class PerfilActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPerfilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.root.setOnApplyWindowInsetsListener { view, insets ->
            insets
        }

        binding.BtnDatosPersonalesPerfil.setOnClickListener{
            val intent = Intent(this, DatosPersonalesActivity::class.java)
            startActivity(intent)
        }

        binding.BtnSeguridadPerfil.setOnClickListener{
            val intetn = Intent(this, ContrasenaSeguridadActiviy::class.java)
            startActivity(intetn)
        }

    }
}
