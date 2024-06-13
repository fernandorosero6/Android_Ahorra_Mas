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

class SimuladorActivity: AppCompatActivity() {

    private lateinit var spinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simulador)

        // spinner = findViewById(R.id.spinnerEstrato)

        val spinner1: Spinner = findViewById(R.id.spinnerContador);
        val spinner2: Spinner = findViewById(R.id.spinnerServicio)



        val listItems1 = listOf("Contador 1","Contador 2","Contador 3", "Contador 4")

        val arrayAdapter1 = ArrayAdapter(this, android.R.layout.simple_spinner_item,listItems1)
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter1



        val listItems2 = listOf("Agua y alcantarillado","Solo agua")

        val arrayAdapter2 = ArrayAdapter(this, android.R.layout.simple_spinner_item,listItems2)
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter2

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                Toast.makeText(this@SimuladorActivity,"you have selected $selectedItem flayour", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }




//modificar direccionanimiento de botón


    lateinit var btnCalcular: Button
    fun OnCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simulador)

        btnCalcular = findViewById(R.id.btnCalcular)

        btnCalcular.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    AgregarActivity2 ::class.java
                )
            )
        }
    }
}