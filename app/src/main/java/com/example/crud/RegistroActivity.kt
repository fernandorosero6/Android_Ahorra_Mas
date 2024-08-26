package com.example.crud

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ahorra.LoginActivity
import com.example.ahorra.R
import com.example.ahorra.databinding.ActivityUsuarioCrearCuentaBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegistroActivity : AppCompatActivity(), UsuarioAdapter.OnItemClicked {

    lateinit var binding: ActivityUsuarioCrearCuentaBinding

    lateinit var adatador: UsuarioAdapter

    var listaUsuarios = arrayListOf<Usuario>()

    var usuario = Usuario(-1, "", "", "", "", "", "")

    var isEditando = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsuarioCrearCuentaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvUsuarios.layoutManager = LinearLayoutManager(this)
        setupRecyclerView()

        obtenerUsuarios()

        binding.btnRegister.setOnClickListener {
            var isValido = validarCampos()
            if (isValido) {
                if (!isEditando) {
                    agregarUsuario()
                } else {
                    actualizarUsuario()
                }
            } else {
                Toast.makeText(this, "Se deben llenar los campos", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun setupRecyclerView() {
        adatador = UsuarioAdapter(this, listaUsuarios)
        adatador.setOnClick(this@RegistroActivity)
        binding.rvUsuarios.adapter = adatador

    }

    fun validarCampos(): Boolean {
        return !(
                binding.EDTRegistroNombre.text.isNullOrEmpty() ||
                        binding.EDTRegistroApellido.text.isNullOrEmpty() ||
                        binding.EDTRegistroDocumento.text.isNullOrEmpty() ||
                        binding.EDTRegistroEmail.text.isNullOrEmpty() ||
                        binding.EDTRegistroContrasena.text.isNullOrEmpty()) ||
                binding.EDTRegistroContrasenaConfirmacion.text.isNullOrEmpty()
    }

    fun obtenerUsuarios() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitClient.webService.obtenerUsuarios()
            runOnUiThread {
                if (call.isSuccessful) {
                    listaUsuarios = call.body()!!.listaUsuarios
                    setupRecyclerView()
                } else {
                    Toast.makeText(this@RegistroActivity, "ERROR CONSULTAR TODOS", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    fun agregarUsuario() {

        this.usuario.idUsuario = -1
        this.usuario.nombre = binding.EDTRegistroNombre.text.toString()
        this.usuario.apellido = binding.EDTRegistroApellido.text.toString()
        this.usuario.documento = binding.EDTRegistroDocumento.text.toString()
        this.usuario.correo = binding.EDTRegistroEmail.text.toString()
        this.usuario.contraseña = binding.EDTRegistroContrasena.text.toString()
        this.usuario.confirmarContraseña = binding.EDTRegistroContrasenaConfirmacion.text.toString()


        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitClient.webService.agregarUsuario(usuario)
            runOnUiThread {
                if (call.isSuccessful) {
                    Toast.makeText(this@RegistroActivity, call.body().toString(), Toast.LENGTH_LONG).show()
                    obtenerUsuarios()
                    limpiarCampos()
                    limpiarObjeto()

                    val intent = Intent(this@RegistroActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()

                } else {
                    Toast.makeText(this@RegistroActivity, "ERROR ADD", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    fun actualizarUsuario() {

        this.usuario.nombre = binding.EDTRegistroNombre.text.toString()
        this.usuario.apellido = binding.EDTRegistroApellido.text.toString()
        this.usuario.documento = binding.EDTRegistroDocumento.text.toString()
        this.usuario.correo = binding.EDTRegistroEmail.text.toString()
        this.usuario.contraseña = binding.EDTRegistroContrasena.text.toString()
        this.usuario.confirmarContraseña = binding.EDTRegistroContrasenaConfirmacion.text.toString()


        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitClient.webService.actualizarUsuario(usuario.idUsuario, usuario)
            runOnUiThread {
                if (call.isSuccessful) {
                    Toast.makeText(this@RegistroActivity, call.body().toString(), Toast.LENGTH_LONG).show()
                    obtenerUsuarios()
                    limpiarCampos()
                    limpiarObjeto()

                    binding.btnRegister.setText("Agregar Usuario")
                    binding.btnRegister.backgroundTintList = resources.getColorStateList(R.color.green)
                    isEditando = false
                }
            }
        }
    }

    fun limpiarCampos() {
        binding.EDTRegistroNombre.setText("")
        binding.EDTRegistroApellido.setText("")
        binding.EDTRegistroDocumento.setText("")
        binding.EDTRegistroEmail.setText("")
        binding.EDTRegistroContrasena.setText("")
        binding.EDTRegistroContrasenaConfirmacion.setText("")
    }

    fun limpiarObjeto() {
        this.usuario.idUsuario = -1
        this.usuario.nombre = ""
        this.usuario.apellido = ""
        this.usuario.documento = ""
        this.usuario.correo = ""
        this.usuario.contraseña = ""
        this.usuario.confirmarContraseña = ""

    }

    override fun editarUsuario(usuario: Usuario) {
        binding.EDTRegistroNombre.setText(usuario.nombre)
        binding.EDTRegistroApellido.setText(usuario.apellido)
        binding.EDTRegistroDocumento.setText(usuario.documento)
        binding.EDTRegistroEmail.setText(usuario.correo)
        binding.EDTRegistroContrasena.setText(usuario.contraseña)
        binding.EDTRegistroContrasenaConfirmacion.setText(usuario.confirmarContraseña)

        binding.btnRegister.setText("Actualizar Usuario")
        binding.btnRegister.backgroundTintList = resources.getColorStateList(R.color.purple_500)
        this.usuario = usuario
        isEditando = true
    }

    override fun borrarUsuario(idUsuario: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitClient.webService.borrarUsuario(idUsuario)
            runOnUiThread {
                if (call.isSuccessful) {
                    Toast.makeText(this@RegistroActivity, call.body().toString(), Toast.LENGTH_LONG).show()
                    obtenerUsuarios()
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



