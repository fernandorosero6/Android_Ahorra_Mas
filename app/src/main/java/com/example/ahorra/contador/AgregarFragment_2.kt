package com.example.ahorra.contador

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.ahorra.R

class AgregarFragment_2 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el diseño del fragmento
        val view = inflater.inflate(R.layout.fragment_agregar2, container, false)

        val radioGroup = view.findViewById<RadioGroup>(R.id.radioGroup)

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.option1 -> {
                    // Acción cuando se selecciona la primera opción
                    Toast.makeText(context, "Principal", Toast.LENGTH_SHORT).show()
                }
                R.id.option2 -> {
                    // Acción cuando se selecciona la segunda opción
                    Toast.makeText(context, "Secundario", Toast.LENGTH_SHORT).show()
                }
            }
        }

        val btnVolver = view.findViewById<Button>(R.id.btnVolver)
        btnVolver.setOnClickListener {
            // Usar findNavController para navegar a otro fragmento si es necesario
            // Si tu lógica requiere solo cerrar el fragmento, puedes omitirlo o usar popBackStack
            findNavController().popBackStack()
        }

        return view
    }
}
