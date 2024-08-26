package com.example.ahorra

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.ComponentActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val screenSplash = installSplashScreen()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Mostrar el splash screen durante 2 segundos
        Handler().postDelayed({
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)}}
//  val apiService = RetrofitClient.apiService

        // Obtener usuario por ID
        // apiService.getUserById(1).enqueue(object : Callback<User> {
           // override fun onResponse(call: Call<User>, response: Response<User>) {
             //   if (response.isSuccessful) {
               //     val user = response.body()
                    // Actualiza la UI con la información del usuario
                //} else {
                    // Manejo de errores
                //}
            //}

            //override fun onFailure(call: Call<User>, t: Throwable) {
                // Manejo de errores
            //}
        //})

        // Obtener todos los usuarios
        //apiService.getAllUsers().enqueue(object : Callback<List<User>> {
          //  override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
            //    if (response.isSuccessful) {
              //      val users = response.body()
                    // Actualiza la UI con la lista de usuarios
                //} else {
                    // Manejo de errores
                //}
            //}

            //override fun onFailure(call: Call<List<User>>, t: Throwable) {
                // Manejo de errores
         //   }
        //})










       // val registerApi = RetrofitHelper.getInstance().create(ContadorApi::class.java)
        // launching a new coroutine
        //Log.d("contadorApi: ", contadorApi.toString())

//        GlobalScope.launch {
  //        if (result != null)
            // Checking the results
    //            Log.d("ayush: ", result.body().toString())
      //  }
        //Log.d("Nada: ","")

        //Iconos answer




//}


