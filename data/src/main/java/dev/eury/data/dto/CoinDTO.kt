package dev.eury.data.dto

data class CoinDTO(
    val availableSupply: Float,
    val exp: List<String>,
    val icon: String,
    val id: String,
    val marketCap: Float,
    val name: String,
    val price: Double,
    val priceBtc: Float,
    val priceChange1d: Double,
    val priceChange1h: Double,
    val priceChange1w: Double,
    val rank: Int,
    val redditUrl: String,
    val symbol: String,
    val totalSupply: Float,
    val twitterUrl: String,
    val volume: Double,
    val websiteUrl: String
)

