package com.example.ahorra

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity

class ContadorActivity : AppCompatActivity(), OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contador)

        // Aquí puedes inicializar tus vistas y configurar los listeners
        // findViewById<Button>(R.id.tu_boton_id).setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        // manejar los clics en los botones aquí
    }
}
