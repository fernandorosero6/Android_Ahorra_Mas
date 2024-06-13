package com.example.ahorra

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ahorra.databinding.ActivityPresupuestoBinding

class PresupuestoActivity : AppCompatActivity() {
    private lateinit var spinner: Spinner
    private lateinit var binding: ActivityPresupuestoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPresupuestoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        spinner = findViewById(R.id.spinnerContador)
        setupSpinner()

        //cambio de activity
        binding.btnSimulador.setOnClickListener{
            val intent = Intent(this, SimuladorActivity::class.java)
            startActivity(intent)
        }

    }

    private fun setupSpinner() {
        val listItems = listOf("Contador 1", "Contador 2", "Contador 3", "Contador 4")
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listItems)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                Toast.makeText(this@PresupuestoActivity, "You have selected $selectedItem flavor", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}

        }
    }
}
