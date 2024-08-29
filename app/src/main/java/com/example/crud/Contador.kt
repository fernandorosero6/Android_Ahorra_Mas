package com.example.crud

data class Contador(
    var idContador: Int,
    var nombre_contador: String,
    var numero_contador: String,
    var barrio: String,
    var direccion: String,
    var tarifa_agua: String,
    var tarifa_alcantarillado: String,
    var ultimo_consumo: String,
    var proximo_pago: String

)