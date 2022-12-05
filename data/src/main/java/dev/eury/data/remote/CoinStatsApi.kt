package dev.eury.data.remote

import dev.eury.data.dto.CoinResponseDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinStatsApi {

    @GET("/public/v1/coins")
    suspend fun getCoins(
        @Query("limit") limit: Int,
        @Query("currency") currency: String
    ) : CoinResponseDTO
}