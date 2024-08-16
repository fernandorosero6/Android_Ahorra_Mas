package com.example.ahorra.interfaces

import com.example.ahorra.Responses.Login
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApiService {

    @POST("login")
    suspend fun createLogin(@Body login: Login): Response<Login>

}
