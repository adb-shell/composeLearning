package com.example.learningcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.learningcompose.ui.theme.BasicsCodelabTheme
import com.example.learningcompose.ui.theme.LearningComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppContent()
        }
    }

    @Composable
    private fun AppContent() {
        val coutner  = remember {
            mutableStateOf(0)
        }
        val list = listOf<String>("karthik","karthik rk")
        val longList = List(100){"Hello item number: $it"}
        BasicsCodelabTheme {
            Surface(color = MaterialTheme.colors.background) {
                    Column {
                        list.forEach { names ->
                            Greetings(name = names)
                            Divider(color = Color.Black)
                        }
                        buttonCounter()
                        buttonCounterWithStateHoisting(count = coutner.value) {newValue->
                            coutner.value = newValue
                        }
                        renderLongList(list = longList, modifier = Modifier.weight(1f))
                    }
            }
        }
    }

    /**
     * Local state for the button with remember keyword.
     */
    @Composable
    fun buttonCounter(){
        val counter = remember {
            mutableStateOf(0)
        }

        Button(onClick = {counter.value++},colors = ButtonDefaults.buttonColors(
            backgroundColor = if (counter.value>5) Color.Yellow else Color.Cyan
        )) {
            Text(text = "Number of times button is clicked:${counter.value}")
        }
    }

    @Composable
    fun renderLongList(list: List<String>,modifier: Modifier){
        LazyColumn() {
            items(list){name->
                Greetings(name = name)
                Divider(color = Color.Black)
            }
        }
    }

    /**
     * Composable function with state hoisting.
     */
    @Composable
    fun buttonCounterWithStateHoisting(count:Int, updateCounter:(counter:Int)->Unit){
        Button(onClick = {updateCounter(count+1)}) {
            Text(text = "Number of times button is clicked:$count")
        }
    }

    @Composable
    fun Greetings(name:String){
        ApplyYellowBg {
            Text(text = "Hello $name",modifier = Modifier.padding(16.dp))
        }
    }

    /**
     * Generic function that applies yellow bg to any composible functions.
     */

    @Composable
    fun ApplyYellowBg(content: @Composable () -> Unit) {
        BasicsCodelabTheme {
            Surface(color = Color.Yellow) {
                content()
            }
        }
    }

    /**
     * @Preview Just to verify with the mock data.
     */
    @Preview(showBackground = true)
    @Composable
    fun preview(){
        AppContent()
    }
}