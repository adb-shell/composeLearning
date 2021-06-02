package com.example.learningcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

    val topics = listOf(
        "Arts & Crafts", "Beauty", "Books", "Business", "Comics", "Culinary",
        "Design", "Fashion", "Film", "History", "Maths", "Music", "People", "Philosophy",
        "Religion", "Social sciences", "Technology", "TV", "Writing"
    )


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
            
            StaggerdGrid(modifier = Modifier.padding(16.dp)) {
                topics.forEach {topic->
                    cardChip(
                        modifier = Modifier.padding(5.dp),
                        content = topic
                    )
                }
            }

            /*CustomColoumn(modifier = Modifier.padding(16.dp)) {
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
            }*/
        }
    }

    @Composable
    private fun cardChip(modifier: Modifier,content:String){
        Row(modifier = modifier) {
            Card(
                border = BorderStroke(color = Color.Black, width = Dp.Hairline),
                shape = MaterialTheme.shapes.large,
                elevation = 3.dp
            ) {
                Row{
                    Box(
                        modifier = Modifier.size(16.dp, 16.dp)
                            .background(color = MaterialTheme.colors.secondary)
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(text = content)
                }
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
     * Complex custom layout staggered grid layout
     * Logic is as follows
     * Step 1: calculate the height and width of each child
     * Step 2: calculate the staggerd grid height by adding all child heights
     * Step 3: calculate the staggerd grid width by adding all child widths
     * Step 4: calculate the Y co-ordinate of each row.
     * Step 5: calculate the X co-ordinate of each child in a row
     * Step 6: place the children's relative to X nd Y co-ordinates calculated in the above steps.
     */
    @Composable
    private fun StaggerdGrid(
        modifier: Modifier,
        rows: Int = 3,
        content: @Composable ()-> Unit
    ){
        Layout(
            content = content,
            modifier = modifier){measurables,constraints->

            val rowWidths = IntArray(rows){0}
            val rowHeights = IntArray(rows){0}

            val placeables = measurables.mapIndexed { index, measurable ->
                val placeable = measurable.measure(constraints)
                val row = index % rows
                rowWidths[row]+=placeable.width
                rowHeights[row] = maxOf(rowHeights[row],placeable.height)
                placeable
            }

            // Grid's height is the sum of the tallest element of each row
            // coerced to the height constraints
            val gridHeight = rowHeights.sumBy { it }
                .coerceIn(constraints.minHeight.rangeTo(constraints.maxHeight))

            val gridWidth = rowWidths.maxOrNull()
                ?.coerceIn(constraints.minWidth.rangeTo(constraints.maxWidth))?:constraints.minWidth

            val rowY = IntArray(rows){0}
            for (i in 1 until rows){
                rowY[i] = rowY[i-1]+rowHeights[i-1]
            }

            layout(gridWidth, gridHeight) {
                val rowX = IntArray(rows){0}
                placeables.mapIndexed { index, placeable ->
                    val row = index % rows
                    placeable.placeRelative(rowX[row],rowY[row])
                    rowX[row]+=placeable.width
                }
            }
        }
    }

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