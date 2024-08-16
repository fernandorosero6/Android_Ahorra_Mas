package com.example.myapp.perfil

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.ahorra.R
import com.example.ahorra.databinding.FragmentDatosBinding

class DatosFragment : Fragment() {

    private var _binding: FragmentDatosBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDatosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configura el listener para el botón Guardar
        binding.btnGuardar.setOnClickListener {
            saveData()
        }
    }

    private fun saveData() {
        // Obtén los valores de los EditTexts
        val nombre = binding.etNombrePerfil.text.toString()
        val apellido = binding.etApellidoPerfil.text.toString()
        val nombreUsuario = binding.etNombreUsuario.text.toString()
        val identificacion = binding.etIdentificacionPerfil.text.toString()
        val fechaNacimiento = binding.etFechaNacimientoPerfil.text.toString()

        // Validar campos
        if (nombre.isEmpty() || apellido.isEmpty() || nombreUsuario.isEmpty() ||
            identificacion.isEmpty() || fechaNacimiento.isEmpty()) {
            // Mostrar mensaje de error si algún campo está vacío
            Toast.makeText(requireContext(), "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        // Aquí puedes agregar la lógica para guardar los datos, por ejemplo, en una base de datos o en preferencias compartidas
        // Por simplicidad, solo mostramos un mensaje de éxito
        Toast.makeText(requireContext(), "Datos guardados exitosamente", Toast.LENGTH_SHORT).show()

        // Navegar a otro fragmento si es necesario
        findNavController().navigate(R.id.action_datosFragment_to_someOtherFragment) // Reemplaza con el ID de tu fragmento de destino
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
