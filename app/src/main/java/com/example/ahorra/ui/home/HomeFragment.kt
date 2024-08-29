package com.example.ahorra.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ahorra.R
import com.example.ahorra.databinding.FragmentHomeBinding
import com.example.ahorra.historial.HistorialFragment
import com.example.ahorra.presupuesto.PresupuestoFragment
import com.example.ahorra.reportes.Reporte_De_Danos
import com.example.crud.EditarContador

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Obtener la instancia del ViewModel
        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        // Inicializar ViewBinding
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Configurar el TextView con el texto del ViewModel
        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        binding.BtnContadores.setOnClickListener {
            // Usa el contexto de la actividad que aloja el fragmento
            startActivity(Intent(requireActivity(), EditarContador::class.java))

        }

        binding.BtnPresupuesto.setOnClickListener {
            replaceFragment(PresupuestoFragment())
        }

        binding.BtnDanos.setOnClickListener {
            replaceFragment(Reporte_De_Danos())
        }

        binding.BtnHistorial.setOnClickListener {
            replaceFragment(HistorialFragment())
        }

        return root
    }

    private fun replaceFragment(fragment: Fragment) {
        // Reemplaza el fragmento actual con el nuevo fragmento
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment) // Cambia R.id.fragment_container por el ID de tu contenedor de fragmentos
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
