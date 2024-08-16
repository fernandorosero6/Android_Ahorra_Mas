package com.example.ahorra.reportes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.ahorra.R

class Reporte_De_Danos : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_reporte_de_danos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializa los botones
        val btnRepor: Button = view.findViewById(R.id.btnRepor)
        val btnHistorial: Button = view.findViewById(R.id.Btn_Histo)
        val btnContacto: Button = view.findViewById(R.id.btnContacto)

        // Configura los listeners para los botones
        btnRepor.setOnClickListener {
            replaceFragment(Asunto_Reporte())  // Reemplaza con el fragmento deseado
        }

        btnHistorial.setOnClickListener {
            replaceFragment(Asunto_Reporte())  // Reemplaza con el fragmento deseado
        }

        btnContacto.setOnClickListener {
            replaceFragment(Asunto_Reporte())  // Reemplaza con el fragmento deseado
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment) // Asegúrate de que este ID es el del contenedor de fragmentos
            .addToBackStack(null)
            .commit()
    }
}
