/*import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

class AuthInterceptor(private var authToken: String) : Interceptor {
    fun setAuthToken(token: String) {
        authToken = token
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder()
            .header("Authorization", "Bearer $authToken") // Modifica el encabezado según sea necesario
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}
*/