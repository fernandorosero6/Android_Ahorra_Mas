package com.example.ahorra.interfaces

import com.example.ahorra.Responses.Asunto

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST



interface AsuntoApi {

    @POST("asunto")
    fun createAsunto(@Body asunto: Asunto): Call<Asunto>


}