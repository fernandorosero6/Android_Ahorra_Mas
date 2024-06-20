package com.example.ahorra

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AgregarActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_agregar2)


        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.option1 -> {
                    // Acción cuando se selecciona la primera opción
                    Toast.makeText(this, "Principal", Toast.LENGTH_SHORT).show()
                }
                R.id.option2 -> {
                    // Acción cuando se selecciona la segunda opción
                    Toast.makeText(this, "Secundario", Toast.LENGTH_SHORT).show()
                }
            }
        }

        val btnDelete = findViewById<Button>(R.id.btnVolver)
        btnDelete.setOnClickListener {
            val intent = Intent(this@AgregarActivity2, AgregarActivity::class.java)
            startActivity(intent)
        }

    }
}