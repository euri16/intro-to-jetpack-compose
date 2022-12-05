package dev.eury.data.remote

import dev.eury.data.common.Constants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CryptoAPIFactory {

    private var api: CoinStatsApi? = null

    fun buildApi(): CoinStatsApi {
        api?.let { return it }

        val client = OkHttpClient.Builder()
        client.addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })

        this.api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinStatsApi::class.java)

        return api!!
    }
}