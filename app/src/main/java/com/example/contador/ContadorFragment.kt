package com.example.contador

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.ahorra.R
import com.example.ahorra.databinding.FragmentContadorBinding

class ContadorFragment : Fragment() {
    private var _binding: FragmentContadorBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Infla el diseño del fragmento
        _binding = FragmentContadorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializa tus vistas y configura los listeners
        val btnAdd: Button = binding.btnAdd
        val btnDelete: Button = binding.btnDelete

        // Configura los listeners para los botones
        btnAdd.setOnClickListener {
            // Reemplaza el fragmento actual con Reporte_De_Danos
            replaceFragment(AgregarFragment())
        }

        btnDelete.setOnClickListener {
            // Reemplaza el fragmento actual con HistorialFragment
            replaceFragment(EliminarContador())
        }

        val loadingImageView: ImageView = view.findViewById(R.id.contador)
        val rotationAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.rotate)
        loadingImageView.startAnimation(rotationAnimation)
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
