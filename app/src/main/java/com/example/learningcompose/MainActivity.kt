package com.example.learningcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.learningcompose.ui.theme.LearningComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Greetings(name = "karthik")
        }
    }

    @Composable
    fun Greetings(name:String){
        Text(text = "Hello $name")
    }

    /**
     * @Preview Just to verify with the mock data.
     */
    @Preview
    @Composable
    fun preview(){
        Greetings(name = "demo app")
    }
}