package com.example.ahorra.Responses

data class Register(
    val id: Int?,
    val name: String,
    val lastName: String,
    val email: String,
    val identification: String,
    val password: String
)
data class RegisterResponse(
    val token: String,
    val userId: Int
)