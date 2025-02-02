import com.example.ahorra.Responses.Asunto
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

private const val BASE_URL = "https://f2cb-181-63-51-244.ngrok-free.app"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface RetrofitApiService {
    @GET("register")
    fun getProperties(): Call<String>

    @FormUrlEncoded
    @POST("login")
    fun postRegProperties(@Field("CoD") CoD: String, @Field("password") password: String): Call<String>

    @FormUrlEncoded
    @POST("register")
    fun postProperties(@Field("name") name: String,
                       @Field("lastName") lastName: String,
                       @Field("identification") identification: String,
                       @Field("email") email: String,
                       @Field("password") password: String): Call<String>

    @FormUrlEncoded
    @PUT("collectors")
    fun putProperties(
        @Field("id") id: String,
        @Field("name") name: String,
        @Field("telephone") telephone: String,
        @Field("email") email: String,
        s: String
    ): Call<String>

    @FormUrlEncoded
    @POST("asunto")
    fun postAsunto(@Field("id") id: String,
                   @Field("nombre") nombre: String,
                   @Field("descripcion") descripcion: String): Call<String>

    abstract fun createAsunto(asunto: Asunto): Call<Asunto>
    abstract fun putProperties(id: String, name: String, telephone: String, email: String): Call<String>
}

object RetrofitApi {
    val retrofitService: RetrofitApiService by lazy {
        retrofit.create(RetrofitApiService::class.java)
    }
}
