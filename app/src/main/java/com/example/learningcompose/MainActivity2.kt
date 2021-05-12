package com.example.learningcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.learningcompose.ui.theme.LearningComposeTheme

class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewStory()
        }
    }

    /**
     * For most of the use cases we can use col / row combination.
     */
    @Composable
    fun NewStory(){
        MaterialTheme {
            Column(
                modifier = Modifier.padding(all = 16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.header),
                    contentDescription = "Header image",
                    modifier = Modifier.height(180.dp).fillMaxWidth().clip(RoundedCornerShape(16.dp,16.dp,16.dp,16.dp)),
                    contentScale = ContentScale.Crop
                )
                Spacer(Modifier.height(16.dp))
                Text(text = "This is the heading in coloumn layout", style = typography.h6)
                Text(text = "This is line number 1 in coloumn layout", style = typography.caption)
                Text(text = "This is line number 2 in coloumn layout",style = typography.caption)
                Text(text = "This is line number 3 in coloumn layout and is the longest whose line is max two ellise " +
                        "at the end of the line very weired although more than this will be cut ",
                    style = typography.caption,maxLines = 2,overflow = TextOverflow.Ellipsis)
            }
        }
    }

    @Preview
    @Composable
    fun demo(){
        NewStory()
    }
}