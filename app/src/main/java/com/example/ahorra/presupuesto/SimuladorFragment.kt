package com.example.ahorra.presupuesto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.ahorra.R

class SimuladorFragment : Fragment() {

    private lateinit var spinnerContador: Spinner
    private lateinit var spinnerServicio: Spinner
    private lateinit var btnCalcular: Button

    // El método onCreateView crea la vista del fragmento.
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Infla el layout para este fragmento
        return inflater.inflate(R.layout.fragment_simulador, container, false)
    }

    // El método onViewCreated se ejecuta después de que se ha creado la vista
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        spinnerContador = view.findViewById(R.id.spinnerContador)
        spinnerServicio = view.findViewById(R.id.spinnerServicio)
        btnCalcular = view.findViewById(R.id.btnCalcular)

        setupSpinners()
       // setupButton()
    }

    private fun setupSpinners() {
        val listItems1 = listOf("Contador 1", "Contador 2", "Contador 3", "Contador 4")
        val arrayAdapter1 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, listItems1)
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerContador.adapter = arrayAdapter1

        val listItems2 = listOf("Agua y alcantarillado", "Solo agua")
        val arrayAdapter2 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, listItems2)
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerServicio.adapter = arrayAdapter2

        spinnerContador.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                Toast.makeText(requireContext(), "Has seleccionado $selectedItem", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
    }

  //  private fun setupButton() {
    //    btnCalcular.setOnClickListener {
         //   startActivity(Intent(requireContext(), AgregarActivity2::class.java))
        //}
  //  }
}
