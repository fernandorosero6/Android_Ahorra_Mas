package com.example.crud

import android.os.Bundle
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
        obtenerUsuarios()

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

    private fun obtenerUsuarios() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitClient.webService.obtenerUsuarios()
            runOnUiThread {
                if (call.isSuccessful) {
                    listaUsuarios = call.body()?.listaUsuarios ?: arrayListOf()
                    adatador.updateList(listaUsuarios)  // Asegúrate de que el adaptador tenga un método para actualizar la lista
                } else {
                    Toast.makeText(this@EditarActivity, "ERROR CONSULTAR TODOS", Toast.LENGTH_LONG).show()
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
            val call = RetrofitClient.webService.actualizarUsuario(usuario.idUsuario.toString(), usuario)
            runOnUiThread {
                if (call.isSuccessful) {
                    Toast.makeText(this@EditarActivity, "Usuario actualizado con éxito", Toast.LENGTH_LONG).show()
                    obtenerUsuarios()
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
        binding.etContraseA.setText(usuario.contraseña)
        binding.etContraseAConfirmar.setText(usuario.confirmarContraseña)

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
                    obtenerUsuarios()
                } else {
                    Toast.makeText(this@EditarActivity, "ERROR DELETE", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun Usuario(
        nombre: String,
        apellido: String,
        documento: String,
        correo: String,
        contraseña: String,
        confirmarContraseña: String
    ): Usuario {
        TODO("Not yet implemented")
    }
}
