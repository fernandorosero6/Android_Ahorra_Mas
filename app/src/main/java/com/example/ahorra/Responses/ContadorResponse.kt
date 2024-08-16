package com.example.ahorra.Responses



data class Contador(
    val nombre_contador: String,
    val num_contador: Int,
    val barrio: String,
    val direccion: String,
    val estrato: Int,
    val categoria: String,
    val tarifa_agua: Double,
    val tarifa_alcantarillado: Double,
    val ultimo_consumo: String,
    val fecha_proximo_pago: String
)