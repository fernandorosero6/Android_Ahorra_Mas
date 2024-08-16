package com.example.ahorra.historial

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ahorra.databinding.ActivityFragmentoHistorialSemanalBinding

class FragmentoHistorialSemanal : Fragment() {
    private var _binding: ActivityFragmentoHistorialSemanalBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ActivityFragmentoHistorialSemanalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}