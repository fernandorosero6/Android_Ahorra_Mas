package com.example.crud

import com.google.gson.annotations.SerializedName

data class ContadoresResponse(

        @SerializedName("listaContadores") var listaContadores: ArrayList<Contador>

)