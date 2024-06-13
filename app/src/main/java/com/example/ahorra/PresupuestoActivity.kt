package com.example.ahorra

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PresupuestoActivity: AppCompatActivity() {

    private lateinit var spinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_presupuesto)

        // spinner = findViewById(R.id.spinnerEstrato)

        val spinner1: Spinner = findViewById(R.id.spinnerContador);

        val listItems1 = listOf("Contador 1","Contador 2","Contador 3", "Contador 4")

        val arrayAdapter1 = ArrayAdapter(this, android.R.layout.simple_spinner_item,listItems1)
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter1



        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                Toast.makeText(this@PresupuestoActivity,"you have selected $selectedItem flayour", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }




//modificar direccionanimiento de botón


    lateinit var btnSimulador: Button
    override fun OnCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_presupuesto)

        btnSimulador = findViewById(R.id.btnSimulador)

        btnSimulador.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    Agregar2Activity ::class.java
                )
            )
        }
    }
}