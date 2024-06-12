package com.example.ahorra

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ahorra.databinding.ActivityFragmentoHistorialAnualBinding

class FragmentoHistorialAnual : Fragment() {
    private var _binding: ActivityFragmentoHistorialAnualBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        saveInstanceState: Bundle?
    ): View?{
        _binding = ActivityFragmentoHistorialAnualBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy(){
        super.onDestroy()
        _binding = null
    }
}