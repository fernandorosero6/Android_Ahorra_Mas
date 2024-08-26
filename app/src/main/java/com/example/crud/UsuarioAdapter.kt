package com.example.crud

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ahorra.R

class UsuarioAdapter(
    private val context: Context,
    private var listausuarios: ArrayList<Usuario>
) : RecyclerView.Adapter<UsuarioAdapter.UsuarioViewHolder>() {

    private var onClick: OnItemClicked? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuarioViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_usuario, parent, false)
        return UsuarioViewHolder(vista)
    }

    override fun onBindViewHolder(holder: UsuarioViewHolder, position: Int) {
        val usuario = listausuarios[position]

        holder.tvIdUsuario.text = usuario.idUsuario.toString()
        holder.tvNombre.text = usuario.nombre
        holder.tvApellido.text = usuario.apellido
        holder.tvDocumento.text = usuario.documento
        holder.tvCorreo.text = usuario.correo
        holder.tvContraseña.text = usuario.contraseña

        holder.btnEditar.setOnClickListener {
            onClick?.editarUsuario(usuario)
        }

        holder.btnBorrar.setOnClickListener {
            onClick?.borrarUsuario(usuario.idUsuario)
        }
    }

    override fun getItemCount(): Int {
        return listausuarios.size
    }

    inner class UsuarioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvIdUsuario: TextView = itemView.findViewById(R.id.tvIdUsuario)
        val tvNombre: TextView = itemView.findViewById(R.id.tvNombre)
        val tvApellido: TextView = itemView.findViewById(R.id.tvApellido)
        val tvDocumento: TextView = itemView.findViewById(R.id.tvDocumento)
        val tvCorreo: TextView = itemView.findViewById(R.id.tvCorreo)
        val tvContraseña: TextView = itemView.findViewById(R.id.tvContraseña)

        val btnEditar: Button = itemView.findViewById(R.id.btnEditar)
        val btnBorrar: Button = itemView.findViewById(R.id.btnBorrar)
    }

    interface OnItemClicked {
        fun editarUsuario(usuario: Usuario)
        fun borrarUsuario(idUsuario: Int)
        abstract fun Usuario(nombre: String, apellido: String, documento: String, correo: String, contraseña: String, confirmarContraseña: String): Usuario
    }

    fun setOnClick(onClick: OnItemClicked?) {
        this.onClick = onClick
    }

    fun updateList(nuevaLista: ArrayList<Usuario>) {
        listausuarios = nuevaLista
        notifyDataSetChanged()
    }
}
