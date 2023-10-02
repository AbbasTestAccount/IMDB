package ir.academy.hamrah.imdb.ui.features.mainScreen


import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import dev.burnoo.cokoin.navigation.getNavViewModel
import ir.academy.hamrah.imdb.R
import ir.academy.hamrah.imdb.utils.TOP_ROW_HEIGHT


@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {

    val context = LocalContext.current
//    val viewModel = getNavViewModel<MainScreenViewModel>()

    val input = remember { mutableStateOf("") }


    val scrollState = rememberScrollState()
    Box(contentAlignment = Alignment.TopStart) {
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
                modifier = Modifier.padding(top = 80.dp)
            ) {

                for (i in 0..10) {
                    Card(
                        modifier = Modifier.size(200.dp),
                        border = BorderStroke(2.dp, Color.Green)
                    ) {

                    }
                }
            }


        }


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {

            IconButton(
                onClick = {},
                modifier = Modifier
                    .size(TOP_ROW_HEIGHT.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.Yellow)
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
                    tint = Color.Red
                )
            }
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth(0.9f),
                    value = input.value,
                    onValueChange = { onValueChanged(it) },
                    label = { Text("جستجو کنید") },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.search_icon),
                            contentDescription = null,
                            modifier = Modifier.size(TOP_ROW_HEIGHT.dp),
                            tint = Color.Black
                        )
                    },

                    colors = TextFieldDefaults.textFieldColors(),
                    singleLine = true,
                    maxLines = 1
                )


            }

        }
    }
}

fun onValueChanged(searchingWord: String) {

}
