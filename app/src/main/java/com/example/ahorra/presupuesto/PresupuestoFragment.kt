package com.example.ahorra.presupuesto

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.ahorra.databinding.FragmentPresupuestoBinding

class PresupuestoFragment : Fragment() {
    private var _binding: FragmentPresupuestoBinding? = null
    private val binding get() = _binding!!

    private lateinit var spinner: Spinner

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPresupuestoBinding.inflate(inflater, container, false)
        val view = binding.root

        spinner = binding.spinnerPresupuesto
        setupSpinner()

        // Cambio de Activity
        binding.btnSimulador.setOnClickListener {
            val intent = Intent(activity, SimuladorFragment::class.java)
            startActivity(intent)
        }

        return view
    }

    private fun setupSpinner() {
        val listItems = listOf("Contador 1", "Contador 2", "Contador 3", "Contador 4")
        val arrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, listItems)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                Toast.makeText(requireContext(), "Has seleccionado el $selectedItem", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Evitar fugas de memoria
    }
}

