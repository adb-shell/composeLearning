package com.example.learningcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.learningcompose.ui.theme.LayoutsCodelabTheme

class ComposeLayoutActivity : ComponentActivity() {

    val bottomList = listOf<String>("Home","Favorite","Add")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Appcontent()
        }
    }

    @Composable
    private fun Appcontent() {
        LayoutsCodelabTheme {
            addScafoldAsRootContent()
        }
    }

    @Composable
    private fun addScafoldAsRootContent() {
        Scaffold(
            topBar = {
                TopAppBar(title = {
                    Text(text = "LayoutsCodelab")
                },
                    actions = {
                        IconButton(onClick = { /* doSomething() */ }) {
                            Icon(Icons.Filled.Favorite, contentDescription = null)
                        }
                        IconButton(onClick = { /* doSomething() */ }) {
                            Icon(Icons.Filled.Add, contentDescription = null)
                        }
                    })
            },
            bottomBar = {
                BottomNavigation(elevation = 3.dp) {
                    /**
                     * Easily bottom items can be rendered with the for loop
                     */
                    bottomList.forEach {bottomTabName->
                        BottomNavigationItem(
                            icon = {Icon(Icons.Filled.Favorite, contentDescription = null)},
                            label = { Text(text = bottomTabName)},
                            selected = true,
                            onClick = {}
                        )
                    }
                }
            }
        ) { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                addPhotographerCard()
                addMaterialButton()
            }
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
}