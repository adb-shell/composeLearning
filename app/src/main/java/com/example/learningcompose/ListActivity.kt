package com.example.learningcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.learningcompose.ui.theme.LayoutsCodelabTheme
import com.google.accompanist.coil.rememberCoilPainter
import kotlinx.coroutines.launch

class ListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            appContent()
        }
    }

    @Composable
    fun appContent(){
        LayoutsCodelabTheme {
            displayList()
        }
    }

    @Composable
    fun displayList(){
        /**
         * Returns the lazy list state that is used across the composition and re-compositon.
         */
        val rememberScrollState = rememberLazyListState()
        /**
         * Returns the coroutine scope that is used across the composition and re-compositon.
         */
        val couroutineScope = rememberCoroutineScope()
        val listSize = 100
        Column {
            Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                Button(onClick = {
                    couroutineScope.launch {
                        rememberScrollState.animateScrollToItem(index = 0)
                    }
                }) {
                    Text(text = "Scroll to the top")
                }

                Button(onClick = {
                    couroutineScope.launch {
                        rememberScrollState.animateScrollToItem(index = listSize-1)
                    }
                }) {
                    Text(text = "Scroll to the bottom")
                }
            }
            LazyColumn(state = rememberScrollState) {
                items(listSize){index->
                    addPhotographerCard(index = index)
                }
            }
        }
    }

    @Composable
    private fun addPhotographerCard(index:Int){
        /** ignore the click **/
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = {
                    /** ignore the click **/
                })
                .background(MaterialTheme.colors.surface)
                .padding(8.dp)
        ){
            /**
             * Dynamically load the image from the remote and load into image view is so easy
             * with the use of the accompanist library.
             */
            Image(painter = rememberCoilPainter(
                request = "https://developer.android.com/images/brand/Android_Robot.png"),
                contentDescription = "Android logo",
                modifier = Modifier.size(50.dp)
            )
            Column(modifier = Modifier
                .padding(8.dp)
                .align(alignment = Alignment.CenterVertically)) {
                Text("Item number is $index")
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text("$index minutes ago", style = MaterialTheme.typography.body2)
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        appContent()
    }
}