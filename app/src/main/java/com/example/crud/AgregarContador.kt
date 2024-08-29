package com.example.crud

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ahorra.LoginActivity
import com.example.ahorra.R
import com.example.ahorra.databinding.ActivityAgregarBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AgregarContador : AppCompatActivity(), ContadorAdapter.OnItemClicked {

    lateinit var binding: ActivityAgregarBinding

    lateinit var adatador: ContadorAdapter

    var listaContadores = arrayListOf<Contador>()

    var contador = Contador(-1, "", "", "", "", "", "","","")

    var isEditando = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgregarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvContadores.layoutManager = LinearLayoutManager(this)
        setupRecyclerView()

        obtenerContadores()

        binding.btnContinuar.setOnClickListener {
            var isValido = validarCampos()
            if (isValido) {
                if (!isEditando) {
                    agregarContador()
                } else {
                    actualizarContador()
                }
            } else {
                Toast.makeText(this, "Se deben llenar los campos", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun setupRecyclerView() {
        adatador = ContadorAdapter(this, listaContadores)
        adatador.setOnClick(this@AgregarContador)
        binding.rvContadores.adapter = adatador

    }

    fun validarCampos(): Boolean {
        return !(
                binding.etNombre.text.isNullOrEmpty() ||
                        binding.etNumero.text.isNullOrEmpty() ||
                        binding.etBarrio.text.isNullOrEmpty() ||
                        binding.etDireccion.text.isNullOrEmpty() ||
                        binding.etTarifaAgua.text.isNullOrEmpty() ||
                        binding.etTarifaAlcantarillado.text.isNullOrEmpty()||
                        binding.etUltimoConsumo.text.isNullOrEmpty() ||
                        binding.etProximoPago.text.isNullOrEmpty())

    }

    fun obtenerContadores() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitClient.webService.obtenerContadores()
            runOnUiThread {
                if (call.isSuccessful) {
                    listaContadores= call.body()!!.listaContadores
                    setupRecyclerView()
                } else {
                    Toast.makeText(this@AgregarContador, "ERROR CONSULTAR TODOS", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    //actualizar nombres de campos en layout agregar contador

    fun agregarContador() {

        this.contador.idContador = -1
        this.contador.nombre_contador = binding.etNombre.text.toString()
        this.contador.numero_contador = binding.etNumero.text.toString()
        this.contador.barrio = binding.etBarrio.text.toString()
        this.contador.direccion = binding.etDireccion.text.toString()
        this.contador.tarifa_agua = binding.etTarifaAgua.text.toString()
        this.contador.tarifa_alcantarillado = binding.etTarifaAlcantarillado.text.toString()
        this.contador.ultimo_consumo = binding.etUltimoConsumo.text.toString()
        this.contador.proximo_pago = binding.etProximoPago.text.toString()

        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitClient.webService.agregarContador(contador)
            runOnUiThread {
                if (call.isSuccessful) {
                    Toast.makeText(this@AgregarContador, call.body().toString(), Toast.LENGTH_LONG).show()
                    obtenerContadores()
                    limpiarCampos()
                    limpiarObjeto()

                    val intent = Intent(this@AgregarContador, EditarContador::class.java)
                    startActivity(intent)
                    finish()

                } else {
                    Toast.makeText(this@AgregarContador, "ERROR ADD", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    fun actualizarContador() {

        this.contador.nombre_contador = binding.etNombre.text.toString()
        this.contador.numero_contador = binding.etNumero.text.toString()
        this.contador.barrio = binding.etBarrio.text.toString()
        this.contador.direccion = binding.etDireccion.text.toString()
        this.contador.tarifa_agua = binding.etTarifaAgua.text.toString()
        this.contador.tarifa_alcantarillado = binding.etTarifaAlcantarillado.text.toString()
        this.contador.ultimo_consumo = binding.etUltimoConsumo.text.toString()
        this.contador.proximo_pago = binding.etProximoPago.text.toString()


        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitClient.webService.actualizarContador(contador.numero_contador, contador)
            runOnUiThread {
                if (call.isSuccessful) {
                    Toast.makeText(this@AgregarContador, call.body().toString(), Toast.LENGTH_LONG).show()
                    obtenerContadores()
                    limpiarCampos()
                    limpiarObjeto()

                    binding.btnContinuar.setText("Agregar Contador")
                    binding.btnContinuar.backgroundTintList = resources.getColorStateList(R.color.green)
                    isEditando = false
                }
            }
        }
    }

    fun limpiarCampos() {
        binding.etNombre.setText("")
        binding.etNumero.setText("")
        binding.etBarrio.setText("")
        binding.etDireccion.setText("")
        binding.etTarifaAgua.setText("")
        binding.etTarifaAlcantarillado.setText("")
        binding.etUltimoConsumo.setText("")
        binding.etProximoPago.setText("")
    }

    fun limpiarObjeto() {
        this.contador.idContador = -1
        this.contador.nombre_contador = ""
        this.contador.numero_contador = ""
        this.contador.barrio = ""
        this.contador.direccion = ""
        this.contador.tarifa_agua = ""
        this.contador.tarifa_alcantarillado = ""
        this.contador.ultimo_consumo = ""
        this.contador.proximo_pago = ""


    }

    override fun editarContador(contador: Contador) {
        // Rellenar los campos con los datos del usuario
        binding.etNombre.setText(contador.nombre_contador)
        binding.etNumero.setText(contador.numero_contador)
        binding.etBarrio.setText(contador.barrio)
        binding.etDireccion.setText(contador.direccion)
        binding.etTarifaAgua.setText(contador.tarifa_agua)
        binding.etTarifaAlcantarillado.setText(contador.tarifa_alcantarillado)
        binding.etUltimoConsumo.setText(contador.ultimo_consumo)
        binding.etProximoPago.setText(contador.proximo_pago)


        binding.btnContinuar.setText("Actualizar Contador")
        binding.btnContinuar.backgroundTintList = resources.getColorStateList(R.color.purple_500)
        this.contador = contador
        isEditando = true
    }

    override fun borrarContador(idContador: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitClient.webService.borrarContador(idContador)
            runOnUiThread {
                if (call.isSuccessful) {
                    Toast.makeText(this@AgregarContador, call.body().toString(), Toast.LENGTH_LONG).show()
                    obtenerContadores()
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



