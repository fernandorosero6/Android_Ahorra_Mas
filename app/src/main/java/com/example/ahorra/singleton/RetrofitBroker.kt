package com.example.ahorra.singleton


import RetrofitApi
import com.example.ahorra.Responses.Asunto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitBroker {



    companion object{



        

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
        

        fun getRequest(onResponse:(resp:String)->Unit, onFailure:(resp:String)->Unit) {
            var r = RetrofitApi.retrofitService.getProperties()
            var p = r.enqueue(
                object : Callback<String> {
                    override fun onFailure(call: Call<String>, t: Throwable) {
                        onFailure(t.message!!)
                    }

                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        onResponse(response.body()!!)
                    }
                })
        }

        fun postRegProperties(body: Map<String, String>, onResponse:(resp:String)->Unit, onFailure:(resp:String)->Unit) : String? {
            var resp: String? = null

            println("body login"+body)

            RetrofitApi.retrofitService.postRegProperties(body["CoD"] ?: "", body["password"] ?: "").enqueue(
                object : Callback<String> {
                    override fun onFailure(call: Call<String>, t: Throwable) {
                        resp = t.message!!.toString()
                        println("mensaje con error" +  t.message!!)
                        onFailure(t.message!!.toString())
                    }

                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        println("mensaje sin error" +  response.body()!!)
                        resp = response.body()!!.toString()
                        println("mensaje sin error resp" +  resp)

                        onResponse(response.body()!!.toString())
                    }
                })
            println("mensaje sin error resp 2" +  resp)

            return resp
        }

        fun postRequest(body: Map<String, String>, onResponse:(resp:String)->Unit, onFailure:(resp:String)->Unit) : String? {
            var resp: String? = null

            println("body[EDT_Registro_nombre]"+ body["EDT_Registro_Email"])
            println("body register"+body)

            RetrofitApi.retrofitService.postProperties(body["name"] ?: "",body["lastName"] ?: "", body["identification"] ?: "", body["email"] ?:"",body["password"] ?:"").enqueue(


                object : Callback<String> {
                    override fun onFailure(call: Call<String>, t: Throwable) {
                        resp = t.message!!.toString()

                        println("faliure registro" + t.message!!.toString())
                        onFailure(t.message!!)
                    }

                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        resp = response.body()!!.toString()

                        onResponse(response.body()!!)
                    }
                })
            return resp
        }
        fun putRequest(body: Map<String, String>, onResponse:(resp:String)->Unit, onFailure:(resp:String)->Unit) : String? {
            var resp: String? = null
            RetrofitApi.retrofitService.putProperties(body[""] ?:"", body["name"] ?: "", body["telephone"] ?: "", body["email"] ?:"").enqueue(
                object : Callback<String> {
                    override fun onFailure(call: Call<String>, t: Throwable) {
                        onFailure(t.message!!)
                    }

                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        onResponse(response.body()!!)
                    }
                })
            return resp
        }
    }
}

private fun Any.enqueue(callback: Callback<Asunto>) {

}
