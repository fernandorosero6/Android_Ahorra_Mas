// AlertaExitosaReporte.kt
package com.example.ahorra.reportes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.ahorra.R
import com.example.ahorra.ui.home.HomeFragment

class AlertaExitosaReporte : Fragment() {

    // Declaración de variables para los componentes
    private lateinit var tvReporte: TextView
    private lateinit var btnAceptar: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Infla el layout para este fragmento
        return inflater.inflate(R.layout.fragment_alerta_exitosa_reporte, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializa los componentes de la vista
        tvReporte = view.findViewById(R.id.TVReporte)
        btnAceptar = view.findViewById(R.id.btnGuardaInic)

        // Configura el listener para el botón Aceptar
        btnAceptar.setOnClickListener {
            replaceFragment(HomeFragment())
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment) // Asegúrate de que este ID es el del contenedor de fragmentos
            .addToBackStack(null)
            .commit()
    }
}



