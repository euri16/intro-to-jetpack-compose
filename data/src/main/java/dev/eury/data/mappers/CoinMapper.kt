package dev.eury.data.mappers

import dev.eury.data.dto.CoinDTO
import dev.eury.domain.model.Coin

class CoinMapper {
    fun toEntity(dto: CoinDTO) = Coin(
        id = dto.id,
        icon = dto.icon,
        name = dto.name,
        rank = dto.rank,
        price = dto.price,
        priceBtc = dto.priceBtc,
        priceChange1h = dto.priceChange1h,
        priceChange1d = dto.priceChange1d,
        priceChange1w = dto.priceChange1w
    )
}