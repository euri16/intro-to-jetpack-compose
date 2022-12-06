package dev.eury.introtojetpackcompose.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import dev.eury.introtojetpackcompose.ui.theme.IntroToJetpackComposeTheme

class MainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme {
                    TODO("[2] Add the UI")
                }
            }
        }
    }

    // TODO [1]: Create the Screen Composable

    // TODO [2]: Create the Item Composable

    // TODO [3]: Create the CoinList Composable & add it to the screen


    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        IntroToJetpackComposeTheme {

        }
    }
}