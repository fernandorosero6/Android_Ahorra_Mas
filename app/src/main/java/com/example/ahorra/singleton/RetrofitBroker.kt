package com.example.ahorra.singleton

import RetrofitApi
import com.example.ahorra.Responses.Asunto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitBroker {

    companion object {

        fun postAsunto(asunto: Asunto, onResponse: (resp: Asunto) -> Unit, onFailure: (resp: String) -> Unit) {
            RetrofitApi.retrofitService.createAsunto(asunto).enqueue(
                object : Callback<Asunto> {
                    override fun onFailure(call: Call<Asunto>, t: Throwable) {
                        onFailure(t.message ?: "Error desconocido")
                    }

                    override fun onResponse(call: Call<Asunto>, response: Response<Asunto>) {
                        if (response.isSuccessful) {
                            onResponse(response.body() ?: Asunto(id = 0, nombre = "", descripcion = ""))
                        } else {
                            onFailure(response.message())
                        }
                    }
                }
            )
        }

        fun getRequest(onResponse: (resp: String) -> Unit, onFailure: (resp: String) -> Unit) {
            val call = RetrofitApi.retrofitService.getProperties()
            call.enqueue(
                object : Callback<String> {
                    override fun onFailure(call: Call<String>, t: Throwable) {
                        onFailure(t.message ?: "Error desconocido")
                    }

                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        if (response.isSuccessful) {
                            onResponse(response.body() ?: "Respuesta vacía")
                        } else {
                            onFailure(response.message())
                        }
                    }
                }
            )
        }

        fun postRegProperties(body: Map<String, String>, onResponse: (resp: String) -> Unit, onFailure: (resp: String) -> Unit): String? {
            var resp: String? = null

            RetrofitApi.retrofitService.postRegProperties(body["CoD"] ?: "", body["password"] ?: "").enqueue(
                object : Callback<String> {
                    override fun onFailure(call: Call<String>, t: Throwable) {
                        resp = t.message ?: "Error desconocido"
                        onFailure(resp!!)
                    }

                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        resp = response.body() ?: "Respuesta vacía"
                        onResponse(resp!!)
                    }
                }
            )
            return resp
        }

        fun postRequest(body: Map<String, String>, onResponse: (resp: String) -> Unit, onFailure: (resp: String) -> Unit): String? {
            var resp: String? = null

            RetrofitApi.retrofitService.postProperties(
                body["name"] ?: "",
                body["lastName"] ?: "",
                body["identification"] ?: "",
                body["email"] ?: "",
                body["password"] ?: ""
            ).enqueue(
                object : Callback<String> {
                    override fun onFailure(call: Call<String>, t: Throwable) {
                        resp = t.message ?: "Error desconocido"
                        onFailure(resp!!)
                    }

                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        resp = response.body() ?: "Respuesta vacía"
                        onResponse(resp!!)
                    }
                }
            )
            return resp
        }
/*
        fun putRequest(body: Int, onResponse: Map<String, String>, onFailure: (resp: String) -> Unit): String? {
            var resp: String? = null
            RetrofitApi.retrofitService.putProperties(
                body["id"] ?: "",
                body["name"] ?: "",
                body["telephone"] ?: "",
                body["email"] ?: ""
            ).enqueue(
                object : Callback<String> {
                    override fun onFailure(call: Call<String>, t: Throwable) {
                        onFailure(t.message ?: "Error desconocido")
                    }

                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        onResponse(response.body() ?: "Respuesta vacía")
                    }
                }
            )
            return resp
        }*/
    }
}
