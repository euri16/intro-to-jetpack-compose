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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import dev.eury.domain.model.Coin
import dev.eury.introtojetpackcompose.ui.theme.bigTextField
import dev.eury.introtojetpackcompose.utils.roundTo

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

    @Composable
    fun CoinListScreen(viewModel: MainViewModel) {
        val state by viewModel.viewState

        var value: String by remember { mutableStateOf("0") }

        Column(
            verticalArrangement = Arrangement.spacedBy((-60).dp)
        ) {
            NumericTextField(value = value, onUpdateValue = { value = it })

            CoinList(coins = state.coins, inputInUSD = value.toInt())
        }
    }

    @Composable
    fun NumericTextField(value: String, onUpdateValue: (String) -> Unit) {
        val pattern = remember { Regex("^\\d+\$") }

        Column(
            modifier = Modifier
                .background(color = Blue200)
                .fillMaxWidth()
                .height(190.dp)
                .padding(vertical = 10.dp),
            verticalArrangement = Arrangement.aligned(Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Insert an amount in USD to convert",
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color(0xFFCECECE)
                )
            )

            Spacer(modifier = Modifier.height(5.dp))

            Row {
                Text("$", style = MaterialTheme.typography.bigTextField)

                Spacer(modifier = Modifier.width(3.dp))

                BasicTextField(
                    modifier = Modifier.width(IntrinsicSize.Min),
                    value = value,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    textStyle = MaterialTheme.typography.bigTextField,
                    onValueChange = { newString ->
                        when {
                            newString.isEmpty() -> onUpdateValue("0")
                            newString.matches(pattern) -> onUpdateValue(
                                if (newString == "0") "0" else newString.trimStart('0')
                            )
                        }
                    }
                )
            }
        }
    }

    @Composable
    fun CoinList(coins: List<Coin>, inputInUSD: Int) {
        Card(
            modifier = Modifier.padding(all = 25.dp),
            elevation = 4.dp
        ) {
            LazyColumn {
                items(coins, key = { it.id }) { coin ->
                    CoinItem(coin, inputInUSD = inputInUSD)
                }
            }
        }
    }

    @Composable
    fun CoinItem(coin: Coin, inputInUSD: Int) {
        val totalCoins = remember(coin, inputInUSD) {
            (inputInUSD / coin.price).roundTo(2)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 25.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CoinInfo(coin)

            Text(text = "$totalCoins ${coin.symbol}")
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

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        IntroToJetpackComposeTheme {
        }
    }
}