package com.example.crud

/*import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ahorra.R
import com.example.ahorra.databinding.ActivityEditarBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditarActivity : AppCompatActivity(), UsuarioAdapter.OnItemClicked {

    private lateinit var binding: ActivityEditarBinding
    private lateinit var adatador: UsuarioAdapter
    private var listaUsuarios = arrayListOf<Usuario>()
    private var usuario = Usuario(-1, "", "", "", "", "", "")

    private var isEditando = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar RecyclerView
        binding.rvUsuarios.layoutManager = LinearLayoutManager(this)
        setupRecyclerView()

        // Obtener el ID del usuario desde el Intent
        val usuarioId = intent.getIntExtra("USER_ID", -1)
        if (usuarioId != -1) {
            obtenerUsuario(usuarioId)
        } else {
            Toast.makeText(this, "ID de usuario no válido", Toast.LENGTH_LONG).show()
        }

        // Inicialmente, solo mostrar el RecyclerView
        binding.formCardView.visibility = View.GONE

        // Configurar el botón para agregar o actualizar usuario
        binding.btnAddUpdate.setOnClickListener {
            if (isEditando) {
                val isValido = validarCampos()
                if (isValido) {
                    actualizarUsuario()
                } else {
                    Toast.makeText(this, "Se deben llenar los campos", Toast.LENGTH_LONG).show()
                }
            } else {
                mostrarFormulario()
            }
        }
    }

    private fun mostrarFormulario() {
        binding.formCardView.visibility = View.VISIBLE
        binding.rvUsuarios.visibility = View.GONE
        binding.btnAddUpdate.text = "Actualizar Usuario"
        binding.btnAddUpdate.setBackgroundColor(resources.getColor(R.color.purple_500))
    }

    private fun mostrarRecyclerView() {
        binding.formCardView.visibility = View.GONE
        binding.rvUsuarios.visibility = View.VISIBLE
    }

    private fun setupRecyclerView() {
        adatador = UsuarioAdapter(this, listaUsuarios)
        adatador.setOnClick(this@EditarActivity)
        binding.rvUsuarios.adapter = adatador
    }

    private fun validarCampos(): Boolean {
        val esValido = !(
                binding.etNombre.text.isNullOrEmpty() ||
                        binding.etApellido.text.isNullOrEmpty() ||
                        binding.etDocumento.text.isNullOrEmpty() ||
                        binding.etCorreo.text.isNullOrEmpty() ||
                        binding.etContraseA.text.isNullOrEmpty() ||
                        binding.etContraseAConfirmar.text.isNullOrEmpty()
                )
        Log.d("ValidacionCampos", "Campos válidos: $esValido")
        return esValido
    }

    private fun obtenerUsuario(idUsuario: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Llamada al servicio web para obtener un usuario específico
                val response = RetrofitClient.webService.obtenerUsuario(idUsuario)

                // Volver al hilo principal para actualizar la UI
                runOnUiThread {
                    if (response.isSuccessful) {
                        val usuarioObtenido = response.body()
                        if (usuarioObtenido != null) {
                            // Rellenar campos con los datos del usuario
                            binding.etNombre.setText(usuarioObtenido.nombre)
                            binding.etApellido.setText(usuarioObtenido.apellido)
                            binding.etDocumento.setText(usuarioObtenido.documento)
                            binding.etCorreo.setText(usuarioObtenido.correo)
                            binding.etContraseA.setText(usuarioObtenido.contraseña)
                            binding.etContraseAConfirmar.setText(usuarioObtenido.confirmarContraseña)

                            this@EditarActivity.usuario = usuarioObtenido
                            isEditando = true

                            mostrarFormulario()
                        } else {
                              Toast.makeText(this@EditarActivity, "Usuario no encontrado", Toast.LENGTH_LONG).show()
                        }
                    } else {
                        Toast.makeText(this@EditarActivity, "ERROR CONSULTAR USUARIO", Toast.LENGTH_LONG).show()
                    }
                }
            } catch (e: Exception) {
                runOnUiThread {
                    Toast.makeText(this@EditarActivity, "ERROR: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun actualizarUsuario() {
        this.usuario.nombre = binding.etNombre.text.toString()
        this.usuario.apellido = binding.etApellido.text.toString()
        this.usuario.documento = binding.etDocumento.text.toString()
        this.usuario.correo = binding.etCorreo.text.toString()
        this.usuario.contraseña = binding.etContraseA.text.toString()
        this.usuario.confirmarContraseña = binding.etContraseA.text.toString()

        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitClient.webService.actualizarUsuario(usuario.idUsuario, usuario)
            runOnUiThread {
                if (call.isSuccessful) {
                    Toast.makeText(this@EditarActivity, "Usuario actualizado con éxito", Toast.LENGTH_LONG).show()
                    obtenerUsuario(usuario.idUsuario) // Refrescar la información del usuario actualizado
                    limpiarCampos()

                    binding.btnAddUpdate.text = "Actualizar Usuario"
                    binding.btnAddUpdate.setBackgroundColor(resources.getColor(R.color.green))
                    isEditando = false
                    mostrarRecyclerView()
                } else {
                    Toast.makeText(this@EditarActivity, "ERROR UPDATE", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun limpiarCampos() {
        binding.etNombre.setText("")
        binding.etApellido.setText("")
        binding.etDocumento.setText("")
        binding.etCorreo.setText("")
        binding.etContraseA.setText("")
        binding.etContraseAConfirmar.setText("")
    }

    override fun editarUsuario(usuario: Usuario) {
        // Rellenar los campos con los datos del usuario
        binding.etNombre.setText(usuario.nombre)
        binding.etApellido.setText(usuario.apellido)
        binding.etDocumento.setText(usuario.documento)
        binding.etCorreo.setText(usuario.correo)
      /*  binding.etContraseA.setText(usuario.contraseña)
        binding.etContraseAConfirmar.setText(usuario.confirmarContraseña)*/

        binding.btnAddUpdate.text = "Actualizar Usuario"
        binding.btnAddUpdate.setBackgroundColor(resources.getColor(R.color.purple_500))
        this.usuario = usuario
        isEditando = true
        mostrarFormulario()
    }

    override fun borrarUsuario(idUsuario: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitClient.webService.borrarUsuario(idUsuario)
            runOnUiThread {
                if (call.isSuccessful) {
                    Toast.makeText(this@EditarActivity, "Usuario borrado con éxito", Toast.LENGTH_LONG).show()
                    // Aquí puedes llamar a obtenerUsuarios() si necesitas actualizar la lista de usuarios después de la eliminación
                } else {
                    Toast.makeText(this@EditarActivity, "ERROR DELETE", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}*/

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ahorra.InicioActivity
import com.example.ahorra.databinding.ActivityEditarBinding
import com.example.ahorra.singleton.RetrofitBroker

class EditarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditarBinding
    private var usuarioId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtener el ID del usuario desde el Intent
        usuarioId = intent.getIntExtra("USER_ID", -1)
        if (usuarioId == -1) {
            Toast.makeText(this, "ID de usuario no válido", Toast.LENGTH_LONG).show()
            finish()
        }

        // Configurar el botón para actualizar el usuario
        binding.btnUpdate.setOnClickListener {
            if (validarCampos()) {
                actualizarUsuario()
            } else {
                Toast.makeText(this, "Se deben llenar todos los campos", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun validarCampos(): Boolean {
        return !(
                binding.etNombre.text.isNullOrEmpty() ||
                        binding.etApellido.text.isNullOrEmpty() ||
                        binding.etDocumento.text.isNullOrEmpty() ||
                        binding.etCorreo.text.isNullOrEmpty()
                )
    }

    private fun actualizarUsuario() {
        val nombre = binding.etNombre.text.toString()
        val apellido = binding.etApellido.text.toString()
        val documento = binding.etDocumento.text.toString()
        val correo = binding.etCorreo.text.toString()

        // Verificar si todos los campos están llenos
        if (nombre.isEmpty() || apellido.isEmpty() || documento.isEmpty() || correo.isEmpty() ) {
            Toast.makeText(this, "Todos los campos deben ser llenados", Toast.LENGTH_LONG).show()
            return
        }

        // Verificar que las contraseñas coincidan


        // Preparar el cuerpo de la solicitud
        val usuario = mapOf(
            "nombre" to nombre,
            "apellido" to apellido,
            "documento" to documento,
            "correo" to correo,

        )

        // Llamar al RetrofitBroker para realizar la actualización
      /*  RetrofitBroker.putRequest(usuarioId, usuario, { respuesta ->
            Toast.makeText(this, respuesta, Toast.LENGTH_LONG).show()
            volverAHome()
        }, { error ->
            Toast.makeText(this, "Error: $error", Toast.LENGTH_LONG).show()
        })*/
    }



    private fun volverAHome() {
        // Crear un Intent para volver a la pantalla de inicio
        val intent = Intent(this, InicioActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        startActivity(intent)
        finish()
    }
}
