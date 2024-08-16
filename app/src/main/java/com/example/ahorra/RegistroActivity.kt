package com.example.ahorra

//import com.example.ahorra.singleton.RetrofitClient
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ahorra.singleton.RetrofitBroker


class RegistroActivity : AppCompatActivity() {

        private lateinit var edtName: EditText
        private lateinit var edtLastName: EditText
        private lateinit var edtEmail: EditText
        private lateinit var edtIdentification: EditText
        private lateinit var edtPassword: EditText
        private lateinit var edtConfirmPassword: EditText
        private lateinit var btnRegister: Button

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_registro)

            // Inicializar vistas
            edtName = findViewById(R.id.EDT_Registro_nombre)
            edtLastName = findViewById(R.id.EDT_Registro_Apellido)
            edtEmail = findViewById(R.id.EDT_Registro_Email)
            edtIdentification = findViewById(R.id.EDT_Registro_Documento)
            edtPassword = findViewById(R.id.EDT_Registro_Contrasena)
            edtConfirmPassword = findViewById(R.id.EDT_Registro_ContrasenaConfirmacion)
            btnRegister = findViewById(R.id.btnRegister)

            btnRegister.setOnClickListener {
                registerUser()
            }
        }

        fun registerUser() {
            val name = edtName.text.toString().trim()
            val lastName = edtLastName.text.toString().trim()
            val email = edtEmail.text.toString()
            val identification = edtIdentification.text.toString().trim()
            val password = edtPassword.text.toString().trim()
            val confirmPassword = edtConfirmPassword.text.toString().trim()



            // Validaciones simples
            if (edtName.text.isEmpty() || edtLastName.text.isEmpty() || edtIdentification.text.isEmpty() || edtEmail.text.isEmpty() ||
                edtPassword.text.isEmpty() || edtConfirmPassword.text.isEmpty()) {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
                return
            }

            if (password != confirmPassword) {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                return
            }

            val body = mutableMapOf("name" to "Value1", "lastName" to "Value2", "identification" to "Value3", "email" to "Value4", "password" to "Value5")

            body["name"] = edtName.text.toString()// "m@gmail.com"
            body["lastName"] = edtLastName.text.toString() //"12345678"//
            body["identification"] = edtIdentification.text.toString()
            body["email"] = edtEmail.text.toString()
            body["password"] = edtPassword.text.toString()

            // Crear un mapa con los datos para enviar
            val body2 = mapOf(
                "name" to name,
                "lastName" to lastName,
                "identification" to identification,
                "email" to email,
                "password" to password
            )

            println("valor de body register"+ body)


            // Llamar al método de registro de Retrofit
            RetrofitBroker.postRequest(body ,
                onResponse = { response ->
                    // Manejar la respuesta exitosa
                    Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
                    // Redirigir a la página de login

                    startActivity(Intent(this, LoginActivity::class.java))
                    finish() // Cerrar RegistroActivity
                },
                onFailure = { error ->
                    // Manejar el error
                    Toast.makeText(this, "Error: "+ error, Toast.LENGTH_SHORT).show()
                })
        }
}
