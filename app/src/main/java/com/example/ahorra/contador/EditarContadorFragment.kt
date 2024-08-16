package com.example.ahorra.contador

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.ahorra.R


class EditarContadorFragment : Fragment() {
private lateinit var spinner1: Spinner
private lateinit var spinner2: Spinner

override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    // Inflar el diseño del fragmento y devolverlo
    val view = inflater.inflate(R.layout.fragment_editar_contador, container, false)

    // Inicializar los spinners
    spinner1 = view.findViewById(R.id.SP_OpcionesEstrato)
    spinner2 = view.findViewById(R.id.SP_OpcionesCategoria)

    // Configurar el primer spinner
    val listItems1 = listOf("1", "2", "3", "4", "5", "6")
    val arrayAdapter1 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, listItems1)
    arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    spinner1.adapter = arrayAdapter1

    // Configurar el segundo spinner
    val listItems2 = listOf("Comercial", "Doméstico", "Industrial")
    val arrayAdapter2 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, listItems2)
    arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    spinner2.adapter = arrayAdapter2

    // Configurar el listener para el primero (o el segundo, según sea necesario)
    spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
            val selectedItem = parent.getItemAtPosition(position).toString()
            Toast.makeText(requireContext(), "You have selected $selectedItem", Toast.LENGTH_SHORT).show()
        }

        override fun onNothingSelected(parent: AdapterView<*>) {
            // No hacer nada
        }
    }

    // Puedes agregar un listener para el segundo spinner si es necesario

    return view
}
}