package com.example.ahorra

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import com.example.ahorra.databinding.ActivityContadorBinding


class ContadorActivity : AppCompatActivity(){
    private lateinit var binding: ActivityContadorBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contador)
        binding = ActivityContadorBinding.inflate(layoutInflater)
        // Aquí puedes inicializar tus vistas y configurar los listeners
        // findViewById<Button>(R.id.tu_boton_id).setOnClickListener(this)

        binding.btnAdd.setOnClickListener{
            val intent = Intent(this, AgregarActivity::class.java)
            startActivity(intent)
        }
    }


    fun onClick(view: View?) {
        // manejar los clics en los botones aquí
    }
}
