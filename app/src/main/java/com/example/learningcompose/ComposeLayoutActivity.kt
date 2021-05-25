package com.example.learningcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.learningcompose.ui.theme.LayoutsCodelabTheme
import com.example.learningcompose.ui.theme.LearningComposeTheme

class ComposeLayoutActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Appcontent()
        }
    }

    @Composable
    private fun addMaterialButton() {
        /**
         * An example of slot API's
         */
        Button(onClick = { /** ignore for now **/ }) {
            Row {
                Image(
                    painter = painterResource(id = R.drawable.header),
                    contentDescription = "",
                    modifier = Modifier
                        .height(24.dp)
                )
                Spacer(modifier = Modifier.padding(4.dp))
                Text("Material Button", fontWeight = FontWeight.Bold)
            }
        }
    }

    @Composable
    private fun addPhotographerCard(){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = {
                    /** ignore the click **/
                })
                .background(MaterialTheme.colors.surface)
                .padding(8.dp)
        ){
            Surface(
                shape = CircleShape,
                modifier = Modifier.size(50.dp),
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)) {

            }
            Column(modifier = Modifier
                .padding(8.dp)
                .align(alignment = Alignment.CenterVertically)) {
                Text("Alfred Sisley", fontWeight = FontWeight.Bold)
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text("3 minutes ago", style = typography.body2)
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        Appcontent()
    }

    @Composable
    private fun Appcontent() {
        LayoutsCodelabTheme {
            Column() {
                addPhotographerCard()
                addMaterialButton()
            }
        }
    }
}