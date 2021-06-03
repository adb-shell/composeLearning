package com.example.learningcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.example.learningcompose.ui.theme.LearningComposeTheme

class ConstraintLayoutActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            appcontent()
        }
    }

    @Composable
    private fun appcontent() {
        LearningComposeTheme {
            Surface(color = MaterialTheme.colors.background) {
                constraintLayout(modifier = Modifier.padding(16.dp))
            }
        }
    }

    @Composable
    fun constraintLayout(modifier: Modifier){
        ConstraintLayout(modifier = modifier) {
            //creating references for the childerns of constraint layout
            val(button1,button2,text) = createRefs()
            Button(onClick = {},modifier = Modifier.constrainAs(button1){
                top.linkTo(parent.top,margin = 16.dp)
                start.linkTo(parent.start,margin = 16.dp)
            }) {
                Text(text = "Button 1")
            }
            Text(text = "Text in between", modifier = Modifier.constrainAs(text){
                top.linkTo(parent.top,margin = 16.dp)
                start.linkTo(button1.end,margin = 16.dp)
                centerHorizontallyTo(parent)
            })
            Button(onClick = {},modifier = Modifier.constrainAs(button2){
                top.linkTo(parent.top,margin = 16.dp)
                start.linkTo(text.end,margin = 16.dp)
                end.linkTo(parent.end,margin = 16.dp)
            }) {
                Text(text = "Button 2")
            }
        }
    }

    /**
     * <b> Decoupled API </b>
     * Were constraint sets are passed dynamically, this helps in passing different constraintSet
     * for each type of the layout.
     */
    @Composable
    fun constraintLayoutWithDynamicConstraintSet(constraintSet: ConstraintSet){
        ConstraintLayout(constraintSet = constraintSet) {
            Button(onClick = {},modifier = Modifier.layoutId("button1")){
                Text(text = "Button 1")
            }
            Text(text = "Text in between",modifier = Modifier.layoutId("text"))
            Button(onClick = {},modifier = Modifier.layoutId("button2")){
                Text(text = "Button 2")
            }
        }
    }

    fun getConstraintSet() = ConstraintSet {
        val button1 = createRefFor("button1")
        val button2 = createRefFor("button2")
        val text = createRefFor("text")

        /**
         * alternatively constraints you can apply based potrait vs landscape
         */
        /** if(potrait) { **/
                constrain(button1){
                    top.linkTo(parent.top,margin = 16.dp)
                    start.linkTo(parent.start,margin = 16.dp)
                }
                constrain(button2){
                    top.linkTo(parent.top,margin = 16.dp)
                    start.linkTo(text.end,margin = 16.dp)
                    end.linkTo(parent.end,margin = 16.dp)
                }
                constrain(text){
                    top.linkTo(parent.top,margin = 16.dp)
                    start.linkTo(button1.end,margin = 16.dp)
                    centerHorizontallyTo(parent)
                }
       /** } else {  some other constraints  } **/
    }

    @Preview
    @Composable
    fun previewOfConstraintLayoutWithConstraints(){
        LearningComposeTheme {
            Surface(color = MaterialTheme.colors.background) {
                constraintLayoutWithDynamicConstraintSet(constraintSet = getConstraintSet())
            }
        }
    }

    @Preview
    @Composable
    fun AppPreview(){
        appcontent()
    }
}