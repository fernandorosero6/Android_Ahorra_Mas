package com.example.ahorra

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ahorra.databinding.ActivityAgregarBinding
import java.util.Calendar

class AgregarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAgregarBinding
    private lateinit var spinner: Spinner
    private lateinit var btnContinuar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




        binding = ActivityAgregarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize spinners
        spinner = findViewById(R.id.spinnerEstrato)
        setupSpinners()

        // Initialize btnContinuar
        val btnContinuar: Button = findViewById(R.id.btnContinuar)

        // Establece un OnClickListener para ir al registro

        btnContinuar.setOnClickListener {
            // Inicia la RegisterActivity
            val intent = Intent(this@AgregarActivity, AgregarActivity2::class.java)
            startActivity(intent)

        }

    }

    private fun setupSpinners() {
        // Spinner 1
        val listItems1 = listOf(1, 2, 3, 4, 5, 6)
        val arrayAdapter1 = ArrayAdapter(this, android.R.layout.simple_spinner_item, listItems1)
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter1

        // Spinner 2
        val spinner2: Spinner = findViewById(R.id.spinnerCategoría)
        val listItems2 = listOf("Comercial", "Domestico", "Industrial")
        val arrayAdapter2 = ArrayAdapter(this, android.R.layout.simple_spinner_item, listItems2)
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner2.adapter = arrayAdapter2

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                Toast.makeText(
                    this@AgregarActivity,
                    "You have selected $selectedItem flavor",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

   
}






