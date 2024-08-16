package com.example.myapp.perfil

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ahorra.R
import com.example.ahorra.databinding.FragmentPerfilBinding
import com.example.ahorra.ui.home.HomeFragment
import android.widget.Button

class PerfilFragment : Fragment() {

    // Declaración de variables para los componentes
    private lateinit var btnDatosPerfil: Button
    private lateinit var btnSeguridad: Button

    private var _binding: FragmentPerfilBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPerfilBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializa los componentes de la vista
        btnDatosPerfil = view.findViewById(R.id.btnDatosPerfil)
        btnSeguridad = view.findViewById(R.id.btnSeguridad)

        // Configura los listeners para los botones
        btnDatosPerfil.setOnClickListener {
            replaceFragment(HomeFragment())
        }

        btnSeguridad.setOnClickListener {
            replaceFragment(HomeFragment())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun replaceFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment) // Asegúrate de que este ID es el del contenedor de fragmentos
            .addToBackStack(null)
            .commit()
    }
}
