package ir.academy.hamrah.imdb.ui.features.movieScreen

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import dev.burnoo.cokoin.navigation.getNavController
import dev.burnoo.cokoin.viewmodel.getViewModel
import ir.academy.hamrah.imdb.R
import ir.academy.hamrah.imdb.model.data.MovieInfo
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
    val screenWidth = configuration.screenWidthDp
    val imageWidth = 3 * screenWidth / 10

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
                        .wrapContentHeight()
                        .offset(y = -(imageHeight * 0.3).dp),
                    shape = RoundedCornerShape(topEnd = 16.dp, topStart = 16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(top = 20.dp, start = 15.dp, end = 15.dp)
                            .fillMaxWidth()
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.weight(2f)) {

                                LeftElementsOfPoster(movieInfo)
                            }
                            Column(modifier = Modifier.weight(1f)) {

                                MoviePoster(imageWidth, movieInfo)
                            }
                        }
                        Spacer(modifier = Modifier.height(20.dp))

                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                            MovieIcons(R.drawable.save_icon, "نشان کردن")
                            MovieIcons(R.drawable.bubble_icon, "56")
                            MovieIcons(R.drawable.heart_icon, "96")
                            MovieIcons(R.drawable.share_icon, "اشتراک")

                        }

                        Text(
                            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit," +
                                    " sed do eiusmod tempor incididunt ut labore et dolore magna" +
                                    " aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco" +
                                    " laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor" +
                                    " in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur." +
                                    " Excepteur sint occaecat cupidatat non proident, sunt in culpa qui" +
                                    " officia deserunt mollit anim id est laborum.",
                            fontSize = 12.sp,
                            textAlign = TextAlign.Justify,
                            lineHeight = 16.sp,
                            modifier = Modifier.padding(end = 10.dp)
                        )
                    }


                }


            }


        }
    }
}

@Composable
fun MovieIcons(iconId: Int, s: String) {
    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(
                RoundedCornerShape(4.dp)
            )
            .size(80.dp)
            .clickable { }) {
        Icon(imageVector = ImageVector.vectorResource(id = iconId), contentDescription = "$s icon")
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = s, fontSize = 14.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun LeftElementsOfPoster(movieInfo: MovieInfo) {
    Column() {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Row() {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.time_icon),
                    contentDescription = "time icon",
                    Modifier.size(18.dp)
                )
                Text(text = calcTime(movieInfo.Runtime), fontSize = 14.sp)

                Spacer(modifier = Modifier.width(10.dp))

                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.view_icon),
                    contentDescription = "view icon",
                    Modifier.size(18.dp)
                )
                Text(text = " " + movieInfo.imdbVotes, fontSize = 14.sp)
            }

            Row {
                Text(text = movieInfo.Year, fontSize = 14.sp)
                Spacer(modifier = Modifier.width(10.dp))
            }

        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = movieInfo.Title,
                    maxLines = 3,
                    fontWeight = FontWeight.Bold,
                    fontSize = titleSize(movieInfo.Title).sp
                )
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 10.dp)
            ) {

                Text(
                    text = "عنوان فارسی",
                    modifier = Modifier.align(Alignment.End),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

            }
        }

        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = movieInfo.Plot,
            fontSize = 12.sp,
            textAlign = TextAlign.Justify,
            lineHeight = 16.sp,
            modifier = Modifier.padding(end = 10.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        Text(text = "IMDB : " + movieInfo.imdbRating)


    }
}

fun titleSize(title: String): Int {
    return if (title.length < 26) {
        18
    } else if (title.length < 34) {
        16
    } else {
        14
    }

}

fun calcTime(runtime: String): String {
    val stringMin = runtime.split(" ")[0]
    val intMin = stringMin.toInt()
    val hour = intMin / 60
    val min = intMin % 60
    return " $hour:$min"
}

@Composable
fun MoviePoster(imageWidth: Int, movieInfo: MovieInfo) {
    Card(
        modifier = Modifier
            .width(imageWidth.dp)
            .height((imageWidth * 1.8).dp)
            .clip(RoundedCornerShape(4.dp))

    ) {
        AsyncImage(
            model = movieInfo.Poster,
            contentDescription = "Poster",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
