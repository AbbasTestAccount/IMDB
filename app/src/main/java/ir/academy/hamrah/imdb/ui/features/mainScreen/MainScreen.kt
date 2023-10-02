package ir.academy.hamrah.imdb.ui.features.mainScreen


import android.util.Log
import android.widget.Space
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import dev.burnoo.cokoin.navigation.getNavViewModel
import ir.academy.hamrah.imdb.R
import ir.academy.hamrah.imdb.ui.theme.LitePurple
import ir.academy.hamrah.imdb.ui.theme.Purple
import ir.academy.hamrah.imdb.utils.TOP_ROW_HEIGHT




@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {

    val context = LocalContext.current
    val viewModel = getNavViewModel<MainScreenViewModel>()
    var focusStateOfSearch by remember { mutableStateOf(false) }

    val input = remember { mutableStateOf("") }

    val scrollState = rememberScrollState()

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val cardWidth = screenWidth / 3 - 30
    viewModel.getMoviesList()

    if (viewModel.moviesList.value.isMovieListEmpty()){
        //todo
        Text(text = "Loading")
    }else{
        Log.e("list1111", viewModel.moviesList.value.Search.toString(), )
        Box(contentAlignment = Alignment.TopStart, modifier = Modifier.padding(horizontal = 15.dp)) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(
                        Color.White
                    )
                    .verticalScroll(scrollState)
                    .padding(bottom = 50.dp)
            ) {


                FlowRow(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .padding(top = 50.dp)
                        .fillMaxWidth(),
                    maxItemsInEachRow = 3
                ) {

                    for (i in 0 until 10) {
                        Column {
                            Card(
                                modifier = Modifier
                                    .width(cardWidth.dp)
                                    .height((cardWidth * 1.8).dp)
                                    .padding(top = 20.dp)
                                    .clip(RoundedCornerShape(4.dp))
                                    .clickable { }
                            ) {
                                AsyncImage(model = viewModel.moviesList.value.Search[i].Poster, contentDescription ="Poster", contentScale = ContentScale.FillHeight, modifier = Modifier.fillMaxWidth())
                            }
                            Spacer(modifier = Modifier.height(5.dp))
                            Row(modifier = Modifier
                                .align(Alignment.Start)
                                .width(cardWidth.dp)) {
                                Spacer(modifier = Modifier.width(5.dp))
                                val title = changeTitle(viewModel.moviesList.value.Search[i].Title)
                                Text(text = title , maxLines = 3, fontWeight = FontWeight.Bold, fontSize = 12.sp, lineHeight = 16.sp)
                            }

                        }
                    }
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
                        onValueChange = { onValueChanged(it, input) },
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

fun onValueChanged(searchingWord: String, input: MutableState<String>) {
    input.value = searchingWord
}

fun changeTitle(title: String): String {
    if (title.length < 34){
        return title
    }else{
        return title.substring(0,34)+"..."
    }
}