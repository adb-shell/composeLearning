package com.example.learningcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
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
        val list = listOf<String>("karthik","karthik rk")
        BasicsCodelabTheme {
            Surface(color = MaterialTheme.colors.background) {
                    Column {
                        list.forEach { names ->
                            Greetings(name = names)
                            Divider(color = Color.Black)
                        }
                    }
            }
        }
    }

    @Composable
    fun Greetings(name:String){
        ApplyYellowBg {
            Text(text = "Hello $name")
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