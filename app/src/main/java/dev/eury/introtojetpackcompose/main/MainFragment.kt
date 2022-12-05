package dev.eury.introtojetpackcompose.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.material.MaterialTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.compose.rememberAsyncImagePainter
import dev.eury.introtojetpackcompose.ui.theme.Blue200
import dev.eury.introtojetpackcompose.ui.theme.IntroToJetpackComposeTheme
import androidx.compose.runtime.setValue
import dev.eury.domain.model.Coin
import dev.eury.introtojetpackcompose.ui.theme.TextStyles

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme {
                    CoinListScreen(viewModel)
                }
            }
        }
    }

    // TODO [1]: Create the Screen Composable
    @Composable
    fun CoinListScreen(viewModel: MainViewModel) {
        val state by viewModel.viewState
        var value: String by remember { mutableStateOf("") }

        Column(
            modifier = Modifier.padding(10.dp)
        ) {

            Column(
                modifier = Modifier
                    .background(color = Blue200)
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row {
                    Text("$",
                        style = TextStyles.bigTextField
                    )
                    BasicTextField(
                        modifier = Modifier.width(IntrinsicSize.Min),
                        value = value,
                        textStyle = TextStyles.bigTextField,
                        onValueChange = {
                            value = it
                        }
                    )
                }
            }

            CoinList(coins = state)
        }
    }

    // TODO [2]: Create the Item Composable
    @Composable
    fun CoinItem(coin: Coin) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 25.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CoinInfo(coin)

            Column {
                Text(text = "0.1 ETH")
                Text(text = "0.4 BTC", color = Color(0xFFAFAFAF))
            }
        }
    }

    @Composable
    fun CoinInfo(coin: Coin) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
                painter = rememberAsyncImagePainter(coin.icon),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(15.dp))
            Column {
                Text(text = coin.name)
                Text(
                    text = coin.symbol,
                    color = Color(0xFFAFAFAF)
                )
            }
        }
    }

    @Composable
    fun CoinList(coins: MainViewModel.ViewState) {
        Card(
            modifier = Modifier.padding(vertical = 25.dp),
            elevation = 4.dp
        ) {
            LazyColumn {
                items(coins, key = { it.id }) { coin ->
                    CoinItem(coin)
                }
            }
        }
    }

    // TODO [3]: Create the CoinList Composable & add it to the screen


    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        IntroToJetpackComposeTheme {
            //CoinListScreen(emptyList())
        }
    }
}