package com.example.ahorra.interfaces

import com.example.ahorra.Responses.Register
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface RegisterApiService {
    @POST("register")
    suspend fun createRegister(@Body register: Register): Response <Register>
    abstract fun <Login> createLogin(login: Login): Any

}


