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
import com.example.ahorra.Agregar2Activity
import com.example.ahorra.R

class AgregarActivity : AppCompatActivity() {

    private lateinit var spinnerEstrato: Spinner
    private lateinit var spinnerCategoria: Spinner
    private lateinit var btnContinuar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar)

        spinnerEstrato = findViewById(R.id.spinnerEstrato)
        spinnerCategoria = findViewById(R.id.spinnerCategoría)
        btnContinuar = findViewById(R.id.btnContinuar)

        val listItemsEstrato = listOf(1, 2, 3, 4, 5, 6)
        val arrayAdapterEstrato = ArrayAdapter(this, android.R.layout.simple_spinner_item, listItemsEstrato)
        arrayAdapterEstrato.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerEstrato.adapter = arrayAdapterEstrato

        val listItemsCategoria = listOf("Comercial", "Domestico", "Industrial")
        val arrayAdapterCategoria = ArrayAdapter(this, android.R.layout.simple_spinner_item, listItemsCategoria)
        arrayAdapterCategoria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCategoria.adapter = arrayAdapterCategoria

        spinnerEstrato.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                Toast.makeText(this@AgregarActivity, "Has seleccionado estrato $selectedItem", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        btnContinuar.setOnClickListener {
            startActivity(Intent(this, Agregar2Activity::class.java))
        }
    }
}
