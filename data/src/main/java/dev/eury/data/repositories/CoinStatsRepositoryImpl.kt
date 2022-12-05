package dev.eury.data.repositories

import dev.eury.data.mappers.CoinMapper
import dev.eury.data.remote.CoinStatsApi
import dev.eury.domain.model.Coin
import dev.eury.domain.repository.CoinStatsRepository

class CoinStatsRepositoryImpl (
    private val api: CoinStatsApi,
    private val coinMapper: CoinMapper
) : CoinStatsRepository {

    override suspend fun getCoins(limit: Int, currency: String): List<Coin> {
        return api.getCoins(limit, currency).coins.map {
            coinMapper.toEntity(it)
        }
    }
}