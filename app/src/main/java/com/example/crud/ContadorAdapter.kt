package com.example.crud

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ahorra.R

class ContadorAdapter(
    private val context: Context,
    private var listacontadores: ArrayList<Contador>
) : RecyclerView.Adapter<ContadorAdapter.ContadorViewHolder>() {

    private var onClick: OnItemClicked? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContadorViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_contador, parent, false)
        return ContadorViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ContadorViewHolder, position: Int) {
        val contador = listacontadores[position]

        holder.tvIdContador.text = contador.idContador.toString()
        holder.tvNombreContador.text = contador.nombre_contador
        holder.tvNumero.text = contador.numero_contador
        holder.tvBarrio.text = contador.barrio
        holder.tvDireccion.text = contador.direccion
        holder.tvTarifaAgua.text = contador.tarifa_agua
        holder.tvTarifaAlcantarillado.text = contador.tarifa_alcantarillado
        holder.tvUltimoConsumo.text = contador.ultimo_consumo
        holder.tvProximoPago.text = contador.proximo_pago

        holder.btnEditar.setOnClickListener {
            onClick?.editarContador(contador)
        }

        holder.btnBorrar.setOnClickListener {
            onClick?.borrarContador(contador.idContador)
        }
    }

    override fun getItemCount(): Int {
        return listacontadores.size
    }

    inner class ContadorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvIdContador: TextView = itemView.findViewById(R.id.tvIdContador)
        val tvNombreContador: TextView = itemView.findViewById(R.id.tvNombreContador)
        val tvNumero: TextView = itemView.findViewById(R.id.tvNumero)
        val tvBarrio: TextView = itemView.findViewById(R.id.tvBarrio)
        val tvDireccion: TextView = itemView.findViewById(R.id.tvDireccion)
        val tvTarifaAgua: TextView = itemView.findViewById(R.id.tvTarifaAgua)
        val tvTarifaAlcantarillado: TextView = itemView.findViewById(R.id.tvTarifaAlcantarillado)
        val tvUltimoConsumo: TextView = itemView.findViewById(R.id.tvUltimoConsumo)
        val tvProximoPago: TextView = itemView.findViewById(R.id.tvProximoPago)

        val btnEditar: Button = itemView.findViewById(R.id.btnEditar)
        val btnBorrar: Button = itemView.findViewById(R.id.btnBorrar)
    }

    interface OnItemClicked {
        fun editarContador(contador: Contador)
        fun borrarContador(idContador: Int)

        abstract fun Contador(nombre_contador: String, numero_contador: String, barrio: String, direccion: String, tarifa_agua: String, tarifa_alcantarillado: String, ultimo_consumo: String, proximo_pago: String): Contador
    }

    fun setOnClick(onClick: OnItemClicked?) {
        this.onClick = onClick
    }

    fun updateList(nuevaLista: ArrayList<Contador>) {
        if (nuevaLista != null) { // Verifica que la lista no sea null
            listacontadores = nuevaLista
            notifyDataSetChanged()
        } else {
            // Manejo del caso en que la lista es null

        }
    }


}
