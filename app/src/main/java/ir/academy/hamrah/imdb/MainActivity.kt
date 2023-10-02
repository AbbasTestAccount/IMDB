package ir.academy.hamrah.imdb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dev.burnoo.cokoin.navigation.KoinNavHost
import ir.academy.hamrah.imdb.ui.features.mainScreen.MainScreen
import ir.academy.hamrah.imdb.ui.features.movieScreen.MovieScreen
import ir.academy.hamrah.imdb.ui.theme.IMDBTheme
import ir.academy.hamrah.imdb.utils.MAIN_SCREEN
import ir.academy.hamrah.imdb.utils.MOVIE_SCREEN

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IMDBTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ImdbUi()
                }
            }
        }
    }
}

@Composable
fun ImdbUi() {

    val navController = rememberNavController()

    KoinNavHost(navController = navController, startDestination = MAIN_SCREEN) {
        composable(MAIN_SCREEN) {
            MainScreen()
        }

        composable(
            route = "$MOVIE_SCREEN/{imdbID}",
            arguments = listOf(navArgument("imdbID") {
                type = NavType.StringType
            })
        ) {
            MovieScreen(it.arguments!!.getString("imdbID", ""))
        }

    }
}