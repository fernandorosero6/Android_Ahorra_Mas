package com.example.ahorra.interfaces

import com.example.ahorra.Responses.Contador
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


interface ContadorApi {
    @GET("contadores")
    fun getContadores(): Call<List<Contador>>

    @POST("contadores")
    fun createContador(@Body contador: Contador): Call<Contador>

    @PUT("contadores/{id}")
    fun updateContador(@Path("id") id: Int, @Body contador: Contador): Call<Contador>

    @DELETE("contadores/{id}")
    fun deleteContador(@Path("id") id: Int): Call<Void>

}