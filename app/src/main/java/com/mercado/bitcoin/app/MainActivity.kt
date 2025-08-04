package com.mercado.bitcoin.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.mercado.bitcoin.core_ui.theme.AppTheme
import com.mercado.bitcoin.exchanges_domain.model.ExchangeData
import com.mercado.bitcoin.exchanges_presentation.exchangeList.ui.ExchangeListScreen
import com.mercado.bitcoin.exchanges_presentation.exchangesDetails.ui.ExchangeDetailsScreen
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = ExchangeListScreenDestination
                ) {
                    composable<ExchangeListScreenDestination> {
                        ExchangeListScreen(
                            navigateToDetailsScreen = {
                                navController.navigate(DetailsScreenDestination)
                            }
                        )
                    }
                    composable<DetailsScreenDestination> {
                        ExchangeDetailsScreen()
                    }
                }
            }
        }
    }
}

@Serializable
object ExchangeListScreenDestination

@Serializable
object DetailsScreenDestination