package com.example.crud

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ahorra.R
import com.example.ahorra.databinding.ActivityEditarContadorBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditarContador : AppCompatActivity(), ContadorAdapter.OnItemClicked {

    private lateinit var binding: ActivityEditarContadorBinding
    private lateinit var adatador: ContadorAdapter
    private var listaContadores = arrayListOf<Contador>()
    private var contador = Contador(-1, "", "", "", "", "", "", "", "")
    private var isEditando = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditarContadorBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Configurar RecyclerView
        binding.rvContadores.layoutManager = LinearLayoutManager(this)
        setupRecyclerView()
        obtenerContadores()


        // Inicialmente, solo mostrar el RecyclerView
        binding.formCardView.visibility = View.GONE

        // Configurar el botón para agregar o actualizar un contador
        binding.btnAddUpdate.setOnClickListener {
            if (isEditando) {
                val isValido = validarCampos()
                if (isValido) {
                    actualizarContador()
                } else {
                    Toast.makeText(this, "Se deben llenar los campos", Toast.LENGTH_LONG).show()
                }
            } else {
                mostrarFormulario()
            }
        }

        // Configurar el botón para agregar un nuevo contador
        binding.btnAdd.setOnClickListener {
            val intent = Intent(this, AgregarContador::class.java)
            startActivity(intent)
        }

    }

    private fun mostrarFormulario() {
        binding.formCardView.visibility = View.VISIBLE
        binding.rvContadores.visibility = View.GONE
        binding.btnAddUpdate.text = "Agregar contador"
        binding.btnAddUpdate.setBackgroundColor(resources.getColor(R.color.green))

    }

    private fun mostrarRecyclerView() {
        binding.formCardView.visibility = View.GONE
        binding.rvContadores.visibility = View.VISIBLE
    }

    private fun setupRecyclerView() {
        adatador = ContadorAdapter(this, listaContadores)
        adatador.setOnClick(this@EditarContador)
        binding.rvContadores.adapter = adatador
    }

    private fun validarCampos(): Boolean {
        val esValido = !(
                binding.etNombre.text.isNullOrEmpty() ||
                        binding.etNumero.text.isNullOrEmpty() ||
                        binding.etBarrio.text.isNullOrEmpty() ||
                        binding.etDireccion.text.isNullOrEmpty() ||
                        binding.etTarifaAgua.text.isNullOrEmpty() ||
                        binding.etTarifaAlcantarillado.text.isNullOrEmpty() ||
                        binding.etUltimoConsumo.text.isNullOrEmpty() ||
                        binding.etProximoPago.text.isNullOrEmpty()
                )
        Log.d("ValidacionCampos", "Campos válidos: $esValido")
        return esValido
    }

    private fun obtenerContadores() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitClient.webService.obtenerContadores()
            runOnUiThread {
                if (call.isSuccessful) {
                    listaContadores = call.body()?.listaContadores ?: arrayListOf()
                    adatador.updateList(listaContadores)  // Asegúrate de que el adaptador tenga un método para actualizar la lista
                } else {
                    Toast.makeText(this@EditarContador, "ERROR CONSULTAR TODOS", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun actualizarContador() {
        this.contador.nombre_contador = binding.etNombre.text.toString()
        this.contador.numero_contador = binding.etNumero.text.toString()
        this.contador.barrio = binding.etBarrio.text.toString()
        this.contador.direccion = binding.etDireccion.text.toString()
        this.contador.tarifa_agua = binding.etTarifaAgua.text.toString()
        this.contador.tarifa_alcantarillado = binding.etTarifaAlcantarillado.text.toString()
        this.contador.ultimo_consumo = binding.etUltimoConsumo.text.toString()
        this.contador.proximo_pago = binding.etProximoPago.text.toString()

      CoroutineScope(Dispatchers.IO).launch {
          val call = RetrofitClient.webService.actualizarContador(contador.idContador.toString(), contador)
          runOnUiThread {
              if (call.isSuccessful) {
                  Toast.makeText(this@EditarContador, "Contador actualizado con éxito", Toast.LENGTH_LONG).show()
                  obtenerContadores()
                  limpiarCampos()

                  binding.btnAddUpdate.text = "Actualizar Contador"
                  binding.btnAddUpdate.setBackgroundColor(resources.getColor(R.color.green))
                  isEditando = false
                  mostrarRecyclerView()
              } else {
                  Toast.makeText(this@EditarContador, "ERROR UPDATE", Toast.LENGTH_LONG).show()
              }
          }
      }
    }

    private fun limpiarCampos() {
        binding.etNombre.setText("")
        binding.etNumero.setText("")
        binding.etBarrio.setText("")
        binding.etDireccion.setText("")
        binding.etTarifaAgua.setText("")
        binding.etTarifaAlcantarillado.setText("")
        binding.etUltimoConsumo.setText("")
        binding.etProximoPago.setText("")
    }

    override fun editarContador(contador: Contador) {
        binding.etNombre.setText(contador.nombre_contador)
        binding.etNumero.setText(contador.numero_contador)
        binding.etBarrio.setText(contador.barrio)
        binding.etDireccion.setText(contador.direccion)
        binding.etTarifaAgua.setText(contador.tarifa_agua)
        binding.etTarifaAlcantarillado.setText(contador.tarifa_alcantarillado)
        binding.etUltimoConsumo.setText(contador.ultimo_consumo)
        binding.etProximoPago.setText(contador.proximo_pago)

        binding.btnAddUpdate.text = "Actualizar contador"
        binding.btnAddUpdate.setBackgroundColor(getColor(R.color.purple_500))
        this.contador = contador
        isEditando = true
        mostrarFormulario()
    }

    override fun borrarContador(idContador: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitClient.webService.borrarContador(idContador)
            runOnUiThread {
                if (call.isSuccessful) {
                    Toast.makeText(this@EditarContador, "Usuario borrado con éxito", Toast.LENGTH_LONG).show()
                    obtenerContadores()
                } else {
                    Toast.makeText(this@EditarContador, "ERROR DELETE", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun Contador(

        nombre_contador: String,
        numero_contador: String,
        barrio: String,
        direccion: String,
        tarifa_agua: String,
        tarifa_alcantarillado: String,
        ultimo_consumo: String,
        proximo_pago: String
    ): Contador {
        TODO("Not yet implemented")
    }
}
