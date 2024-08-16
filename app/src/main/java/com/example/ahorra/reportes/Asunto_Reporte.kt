package com.example.ahorra.reportes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.ahorra.R

class Asunto_Reporte : Fragment() {

    // Declaración de variables para los componentes
    private lateinit var spinnerAsunto: Spinner
    private lateinit var spinnerElegir: Spinner
    private lateinit var descripcionReporte: EditText
    private lateinit var btnEnviar: Button

    // Configuración de Retrofit

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_asunto_reporte, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializa los componentes de la vista
        spinnerAsunto = view.findViewById(R.id.spiAsunto)
        spinnerElegir = view.findViewById(R.id.spiElegir)
        descripcionReporte = view.findViewById(R.id.descripcionReporte)
        btnEnviar = view.findViewById(R.id.btEnviar)

        // Configura los adaptadores para los Spinners
        setupSpinners()

        // Configura el listener para el botón Enviar
        btnEnviar.setOnClickListener {
            handleSend()
        }
    }

    private fun setupSpinners() {
        // Datos de ejemplo para los Spinners (deberías reemplazarlos con datos reales)
        val asuntos = listOf("Daños", "Reclamos", "Asunto")
        val contadores = listOf("Contador 1", "Contador 2", "Contador 3")

        // Configura el adaptador para el spinner de Asunto
        val adapterAsunto = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            asuntos
        )
        adapterAsunto.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerAsunto.adapter = adapterAsunto

        // Configura el adaptador para el spinner de Elegir
        val adapterElegir = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            contadores
        )
        adapterElegir.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerElegir.adapter = adapterElegir
    }

    private fun handleSend() {
        // Obtén los valores seleccionados en los Spinners
        val asuntoSeleccionado = spinnerAsunto.selectedItem.toString()
        val elegirSeleccionado = spinnerElegir.selectedItem.toString()
        val descripcion = descripcionReporte.text.toString()

        // Validar campos
        if (asuntoSeleccionado.isEmpty() || elegirSeleccionado.isEmpty() || descripcion.isEmpty()) {
            // Mostrar mensaje de error si algún campo está vacío
            Toast.makeText(requireContext(), "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        // Si todos los campos están completos, navega al siguiente fragmento
        replaceFragment(AlertaExitosaReporte()) // Reemplaza con el fragmento deseado
    }

    private fun replaceFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment) // Asegúrate de que este ID es el del contenedor de fragmentos
            .addToBackStack(null)
            .commit()
    }
}
