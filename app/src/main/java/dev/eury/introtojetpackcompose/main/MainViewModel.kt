package dev.eury.introtojetpackcompose.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.eury.data.mappers.CoinMapper
import dev.eury.data.remote.CryptoAPIFactory
import dev.eury.data.repositories.CoinStatsRepositoryImpl
import dev.eury.domain.model.Coin
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repository = CoinStatsRepositoryImpl(
        CryptoAPIFactory.buildApi(),
        CoinMapper()
    )

    var viewState = mutableStateOf(ViewState(emptyList()))

    init {
        viewModelScope.launch {
            val coins = repository.getCoins(10, "USD")
            viewState.value = viewState.value.copy(coins = coins)
        }
    }

    data class ViewState(
        val coins: List<Coin> = emptyList(),
    )
}