package com.example.crud

data class LoginResponse(
    val success: Boolean,
    val message: String,
    val token: String?
)
data class Usuario(
    var idUsuario: Int,
    var nombre: String,
    var apellido: String,
    var documento: String,
    var correo: String,
    var contraseña: String,
    var confirmarContraseña: String
)


