package ir.academy.hamrah.imdb.ui.features.movieScreen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.burnoo.cokoin.navigation.getNavController
import dev.burnoo.cokoin.viewmodel.getViewModel
import ir.academy.hamrah.imdb.R
import ir.academy.hamrah.imdb.ui.theme.LitePurple
import ir.academy.hamrah.imdb.ui.theme.LitePurpleOpacity
import ir.academy.hamrah.imdb.ui.theme.Purple
import ir.academy.hamrah.imdb.utils.TOP_ROW_HEIGHT

@Composable
fun MovieScreen(id: String) {
    val viewModel = getViewModel<MovieScreenViewModel>()
    viewModel.getMovieInfo(id)

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp
    val imageHeight = 3 * screenHeight / 10

    if (viewModel.movieInfo.value.imdbID == "") {
        //todo: loading
    } else {
        val movieInfo = viewModel.movieInfo.value
        val navController = getNavController()



        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()

                .verticalScroll(rememberScrollState())
                .padding(bottom = 50.dp)
        ) {


            Column() {


                Box(contentAlignment = Alignment.Center) {
                    AsyncImage(
                        model = movieInfo.Poster,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(imageHeight.dp),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .height((imageHeight * 0.4).dp),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        IconButton(
                            onClick = {},
                            modifier = Modifier
                                .size(54.dp)
                                .clip(CircleShape)
                                .background(LitePurpleOpacity)

                        ) {
                            Icon(
                                modifier = Modifier
                                    .size(54.dp)
                                    .clickable {}
                                    .padding(10.dp),
                                imageVector = ImageVector.vectorResource(id = R.drawable.play_icon),
                                contentDescription = "play icon",
                                tint = Purple
                            )
                        }

                    }


                    Row(
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .height((imageHeight * 0.8).dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {

                        IconButton(
                            onClick = {},
                            modifier = Modifier
                                .size(TOP_ROW_HEIGHT.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(LitePurpleOpacity)

                        ) {
                            Icon(
                                modifier = Modifier
                                    .size(TOP_ROW_HEIGHT.dp)
                                    .clickable {
                                        navController.popBackStack()
                                    }
                                    .padding(5.dp),
                                imageVector = ImageVector.vectorResource(id = R.drawable.left_arrow_icon),
                                contentDescription = "back icon",
                                tint = Purple
                            )
                        }


                        IconButton(
                            onClick = {},
                            modifier = Modifier
                                .size(TOP_ROW_HEIGHT.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(LitePurpleOpacity)

                        ) {
                            Icon(
                                modifier = Modifier
                                    .size(TOP_ROW_HEIGHT.dp)
                                    .clickable { }
                                    .padding(5.dp),
                                imageVector = ImageVector.vectorResource(id = R.drawable.more_icon),
                                contentDescription = "more icon",
                                tint = Purple
                            )
                        }
                    }

                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(420.dp)
                        .offset(y = -(imageHeight * 0.3).dp),
                    shape = RoundedCornerShape(topEnd = 16.dp, topStart = 16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(modifier = Modifier.padding(top = 20.dp, start = 15.dp, end = 15.dp)) {
                        Text(text = "alsjkhjghgvad")
                        Spacer(modifier = Modifier.height(200.dp))
                        Text(text = "alsjkhjghgvad")
                    }


                }


            }


        }
    }
}