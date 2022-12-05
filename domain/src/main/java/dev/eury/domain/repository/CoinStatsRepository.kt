package dev.eury.domain.repository

import dev.eury.domain.model.Coin
import dev.eury.domain.common.Constants.DEFAULT_CURRENCY
import dev.eury.domain.common.Constants.MAX_COINS_ITEMS

interface CoinStatsRepository {
    suspend fun getCoins(limit: Int = MAX_COINS_ITEMS, currency: String = DEFAULT_CURRENCY) : List<Coin>
}