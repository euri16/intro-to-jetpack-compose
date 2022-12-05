package dev.eury.domain.model

data class Coin(
    val id: String,
    val icon: String?,
    val name: String,
    val rank: Int,
    val price: Double,
    val priceBtc: Float,
    val priceChange1d: Double,
    val priceChange1h: Double,
    val priceChange1w: Double,
)