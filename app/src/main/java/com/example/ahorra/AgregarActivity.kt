package com.example.ahorra

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class AgregarActivity: AppCompatActivity() {

    private lateinit var spinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar)

       // spinner = findViewById(R.id.spinnerEstrato)

        val spinner1: Spinner = findViewById(R.id.spinnerEstrato);
        val spinner2: Spinner = findViewById(R.id.spinnerCategoría)



        val listItems1 = listOf(1,2,3,4,5,6)

        val arrayAdapter1 = ArrayAdapter(this, android.R.layout.simple_spinner_item,listItems1)
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter1



        val listItems2 = listOf("Comercial","Domestico","Industrial")

        val arrayAdapter2 = ArrayAdapter(this, android.R.layout.simple_spinner_item,listItems2)
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter2

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
               val selectedItem = parent.getItemAtPosition(position).toString()
                Toast.makeText(this@AgregarActivity,"you have selected $selectedItem flayour", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }




//modificar direccionanimiento de botón


    lateinit var btnContinuar:Button
    override fun OnCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar)

        btnContinuar = findViewById(R.id.btnContinuar)

        btnContinuar.setOnClickListener {
            startActivity(
                Intent(
                    this,
                   Agregar2Activity ::class.java
                )
            )
        }
    }
}


