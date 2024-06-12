package com.example.ahorra

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ahorra.databinding.ActivityHistorialBinding


class HistorialActivity: AppCompatActivity(){
    private lateinit var binding: ActivityHistorialBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityHistorialBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //Funcion para que se cambie de fragmento
        val view = binding.root
        setContentView(view)
        //Aqui cargamos el primer fragmento
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainer, FragmentoHistorialSemanal())
            commit()
        }
        //Al precionar el boton se hara el cambio de fragmento
        binding.BtnHistorialSemanal.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainer, FragmentoHistorialSemanal())
                commit()
            }
        }

        binding.BtnHistorialMensual.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainer, FragmentoHistorialMensual())
                commit()
            }
        }

        binding.BtnHistorialAnual.setOnClickListener {
            supportFragmentManager.beginTransaction().apply{
                replace(R.id.fragmentContainer, FragmentoHistorialAnual())
                commit()
            }
        }

    }
}


