package com.example.ahorra.perfil

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.ahorra.R
import com.example.crud.EditarActivity

class DatosFragment : Fragment() {

    private lateinit var btnGuardar: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout para este fragmento
        return inflater.inflate(R.layout.fragment_datos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnGuardar = view.findViewById(R.id.btnGuardar) // Asegúrate de inicializar el botón correctamente

        btnGuardar.setOnClickListener {
            startActivity(Intent(requireContext(), EditarActivity::class.java))
            Toast.makeText(requireContext(), "Tu perfil ha sido actualizado exitosamente", Toast.LENGTH_SHORT).show()
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment) // Cambia R.id.fragment_container por el ID de tu contenedor de fragmentos
            .addToBackStack(null)
            .commit()
    }
}
