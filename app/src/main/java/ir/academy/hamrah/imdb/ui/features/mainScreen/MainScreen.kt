package ir.academy.hamrah.imdb.ui.features.mainScreen


import android.util.Log
import android.widget.Toast
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import dev.burnoo.cokoin.navigation.getNavController
import dev.burnoo.cokoin.navigation.getNavViewModel
import ir.academy.hamrah.imdb.R
import ir.academy.hamrah.imdb.ui.theme.LitePurple
import ir.academy.hamrah.imdb.ui.theme.Purple
import ir.academy.hamrah.imdb.utils.MOVIE_SCREEN
import ir.academy.hamrah.imdb.utils.TOP_ROW_HEIGHT
import kotlinx.coroutines.launch


@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {

    val context = LocalContext.current
    val viewModel = getNavViewModel<MainScreenViewModel>()
    val navController = getNavController()
    val focusStateOfSearch by remember { mutableStateOf(false) }

    val input = remember { mutableStateOf("") }

    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()


    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val cardWidth = screenWidth / 3 - 30
    viewModel.getMoviesList()

    if (viewModel.moviesList.value.isMovieListEmpty()) {
        //todo
        Text(text = "Loading")
    } else {
        Log.e("list1111", viewModel.moviesList.value.Response)
        Box(
            contentAlignment = Alignment.TopStart,
            modifier = Modifier.padding(horizontal = 15.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(
                        Color.White
                    )
                    .verticalScroll(scrollState)
            ) {


                FlowRow(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .padding(top = 50.dp)
                        .fillMaxWidth(),
                    maxItemsInEachRow = 3
                ) {

                    if (!viewModel.moviesList.value.Search.isNullOrEmpty()) {
                        for (i in 0 until viewModel.moviesList.value.Search.size) {
                            Column {
                                Card(
                                    modifier = Modifier
                                        .width(cardWidth.dp)
                                        .height((cardWidth * 1.8).dp)
                                        .padding(top = 20.dp)
                                        .clip(RoundedCornerShape(4.dp))
                                        .clickable {
                                            navController.navigate("$MOVIE_SCREEN/${viewModel.moviesList.value.Search[i].imdbID}")

                                        }
                                ) {
                                    AsyncImage(
                                        model = viewModel.moviesList.value.Search[i].Poster,
                                        contentDescription = "Poster",
                                        contentScale = ContentScale.FillHeight,
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }
                                Spacer(modifier = Modifier.height(5.dp))
                                Row(
                                    modifier = Modifier
                                        .align(Alignment.Start)
                                        .width(cardWidth.dp)
                                ) {
                                    Spacer(modifier = Modifier.width(5.dp))
                                    val title =
                                        changeTitle(viewModel.moviesList.value.Search[i].Title)
                                    Text(
                                        text = title,
                                        maxLines = 3,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 12.sp,
                                        lineHeight = 16.sp
                                    )
                                }

                            }
                        }

                    }
                }
                if (!viewModel.moviesList.value.Search.isNullOrEmpty()) {
                    Spacer(modifier = Modifier.height(10.dp))

                    PageSelect(
                        viewModel.moviesList.value.totalResults.toInt(),
                        viewModel.pageNumber
                    ) {
                        viewModel.clearMoviesList()
                        coroutineScope.launch {
                            scrollState.animateScrollTo(0, tween(300))
                        }
                    }
                } else {
                    Text(
                        text = "There is no result",
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .align(Alignment.CenterHorizontally)
                            .padding(vertical = 70.dp),
                        textAlign = TextAlign.Center
                    )
                }


            }


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(color = Color.White),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {


                IconButton(
                    onClick = {},
                    modifier = Modifier
                        .size(TOP_ROW_HEIGHT.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(LitePurple)

                ) {
                    Icon(
                        modifier = Modifier
                            .size(TOP_ROW_HEIGHT.dp)
                            .clickable {
                                Toast
                                    .makeText(context, "clicked !!!", Toast.LENGTH_SHORT)
                                    .show()
                            }
                            .padding(5.dp),
                        imageVector = ImageVector.vectorResource(id = R.drawable.filter_icon),
                        contentDescription = "Favorite",
                        tint = Purple
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp),
                        value = input.value,
                        onValueChange = { onValueChanged(it, input, viewModel.searchTerm) },
                        placeholder = {
                            Text(
                                text = if (focusStateOfSearch) "" else "جستجو کنید"
                            )
                        },
                        shape = RoundedCornerShape(8.dp),
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.search_icon),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(TOP_ROW_HEIGHT.dp)
                                    .padding(10.dp),
                                tint = Purple
                            )
                        },

                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            containerColor = LitePurple
                        ),

                        singleLine = true,
                        maxLines = 1
                    )
                }


            }
        }

    }

}

@Composable
fun PageSelect(movieCount: Int, pageNumber: MutableState<Int>, onPageSelect: () -> Unit) {

    LazyRow {
        items(movieCount / 10 + lastPage(movieCount)) {
            IconButton(
                onClick = {
                    onPageSelect()
                    pageNumber.value = it + 1
                },
                modifier = Modifier.background(
                    color = pageSelectColor(it + 1, pageNumber.value),
                    shape = CircleShape
                )
            ) {
                Text(text = (it + 1).toString(), textAlign = TextAlign.Center)
            }

        }

    }
}

fun lastPage(categoryProductCount: Int): Int {
    return if (categoryProductCount % 10 == 0) {
        0
    } else {
        1
    }
}

fun pageSelectColor(i: Int, intValue: Int): Color {
    return if (i == intValue) {
        LitePurple
    } else {
        Color.Transparent
    }
}


private fun onValueChanged(
    searchingWord: String,
    input: MutableState<String>,
    searchTerm: MutableState<String>
) {
    input.value = searchingWord
    if (input.value.length > 2) {
        searchTerm.value = input.value
    }
}

fun changeTitle(title: String): String {
    return if (title.length < 34) {
        title
    } else {
        title.substring(0, 34) + "..."
    }
}