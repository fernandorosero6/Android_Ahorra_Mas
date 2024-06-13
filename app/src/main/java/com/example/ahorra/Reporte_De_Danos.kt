package com.example.ahorra

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ahorra.databinding.ActivityReporteDeDanosBinding
import android.view.View.OnClickListener

class ReporteDeDanosActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReporteDeDanosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReporteDeDanosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRepor.setOnClickListener{
            val intent = Intent(this, Asunto_Reporte::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnContacto.setOnClickListener{
            val intent = Intent(this, Conctacto_Directo::class.java)
            startActivity(intent)
            finish()
        }

    }
}
