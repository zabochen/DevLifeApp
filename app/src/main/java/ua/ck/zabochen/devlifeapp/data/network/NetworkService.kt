package ua.ck.zabochen.devlifeapp.data.network

import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://developerslife.ru"

interface NetworkService {

    // Don't use "suspend"
    @GET("/latest/{page}")
    fun getLatestEntry(@Path("page") page: Int = 0): Call<String>

    companion object {
        operator fun invoke(): NetworkService {

            val okHttpClient = OkHttpClient.Builder()
                .addNetworkInterceptor(StethoInterceptor())
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
                .create(NetworkService::class.java)
        }
    }
}