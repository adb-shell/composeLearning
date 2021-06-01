package com.example.learningcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.learningcompose.ui.theme.LearningComposeTheme

class CustomLayoutActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearningComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Appcontent()
                }
            }
        }
    }

    @Composable
    private fun Appcontent() {
        Column() {
            //text with the custom modifier
            Text(
                text = "This is text with the custom modifier",
                modifier = Modifier.CustomPadding(42.dp),
                fontStyle = FontStyle.Italic
            )

            CustomColoumn(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "This is text in custom col row 1",
                    modifier = Modifier.padding(42.dp),
                    fontStyle = FontStyle.Normal
                )
                Text(
                    text = "This is text in custom col row 2",
                    modifier = Modifier.padding(42.dp),
                    fontStyle = FontStyle.Italic
                )
                Text(
                    text = "This is text in custom col row 3",
                    modifier = Modifier.padding(42.dp),
                    fontStyle = FontStyle.Normal
                )
            }
        }
    }


    /**
     * Custom layout modifier example
     */
    fun Modifier.CustomPadding(
        firstBaselineToTop: Dp
    ) = this.then(layout {
        //child layout to be measured and placed.
            measurable,
            //min max height and width of child.
            constraints ->

        /**
         * The result of calling measure on the child layout returns [Placeable] layout
         * [Placeable] layout is a layout that can be positioned within its parent layout.
         */
        val placeable = measurable.measure(constraints)

        // Check the composable has a first baseline
        check(placeable[FirstBaseline] != AlignmentLine.Unspecified)
        val firstBaseline = placeable[FirstBaseline]

        // Height of the composable with padding - first baseline
        val placeableY = firstBaselineToTop.roundToPx() - firstBaseline
        val height = placeable.height + placeableY

        //finally placing the layout
        layout(placeable.width, height) {
            placeable.placeRelative(0,placeableY)
        }
    })

    /**
     * Custom layout composable example
     */
    @Composable
    private fun CustomColoumn(
        modifier: Modifier,
        content: @Composable ()->Unit
    ){
        Layout(
            modifier = modifier,
            content = content
        ){measurables,constraints->

            val placeables = measurables.map { measurable ->
                measurable.measure(constraints)
            }

            layout(constraints.maxWidth, constraints.maxHeight) {
                var placeableYposition = 0
                placeables.forEach { placeable ->
                    placeable.placeRelative(0,placeableYposition)
                    placeableYposition += placeable.height
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        LearningComposeTheme {
            Appcontent()
        }
    }
}