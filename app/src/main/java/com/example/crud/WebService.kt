package com.example.crud

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.Call
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

object AppConstantes {
    const val BASE_URL = "http://10.0.2.2:3000/"
}

interface WebService {

    // Usuarios
    @GET("/usuarios")
    suspend fun obtenerUsuarios(): Response<UsuariosResponse>

    @GET("/usuario/{idUsuario}")
    suspend fun obtenerUsuario(
        @Path("idUsuario") idUsuario: Int
    ): Response<Usuario>

    @POST("/usuario/add")
    suspend fun agregarUsuario(
        @Body usuario: Usuario
    ): Response<String>

    @PUT("/usuario/update/{idUsuario}")
    suspend fun actualizarUsuario(
        @Path("idUsuario") idUsuario: String,
        @Body usuario: Usuario
    ): Response<String>

    @FormUrlEncoded
    @POST("/login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<String>

    // Contadores
    @GET("/contadores")
    suspend fun obtenerContadores(): Response<ContadoresResponse>

    @GET("/contador/{idContador}")
    suspend fun obtenerContador(
        @Path("idContador") idContador: Int
    ): Response<Contador>

    @POST("/contador/add")
    suspend fun agregarContador(
        @Body contador: Contador
    ): Response<String>

    @PUT("/contador/update/{idContador}")
    suspend fun actualizarContador(
        @Path("idContador") idContador: String,
        @Body contador: Contador
    ): Response<String>

    @DELETE("/usuario/delete/{idUsuario}")
    suspend fun borrarUsuario(
        @Path("idUsuario") idUsuario: Int
    ): Response<String>

    @DELETE("/contador/delete/{idContador}")
    suspend fun borrarContador(
        @Path("idContador") idContador: Int
    ): Response<String>
}

object RetrofitClient {

    private var authToken: String = "" // Token inicial vacío

    private val authInterceptor = AuthInterceptor(authToken)

    private val client = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .build()

    val webService: WebService by lazy {
        Retrofit.Builder()
            .baseUrl(AppConstantes.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(WebService::class.java)
    }

    fun updateAuthToken(token: String) {
        authToken = token
        authInterceptor.setAuthToken(token)
    }
}
