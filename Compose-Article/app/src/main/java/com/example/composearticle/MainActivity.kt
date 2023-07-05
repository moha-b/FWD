package com.example.composearticle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composearticle.ui.theme.ComposeArticleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeArticleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App(getString(R.string.first_text),getString(R.string.second_text),getString(R.string.third_text))
                }
            }
        }
    }
}

@Composable
fun Banner(){
    val  image = painterResource(id = R.drawable.bg_compose_background)
    Image(painter = image,
        contentDescription = null,
        contentScale = ContentScale.FillWidth
    )
}
@Composable
fun App(firstText: String,SecondText: String,thirdText: String , modifier: Modifier = Modifier) {
    Column {
        Banner()
        Text(
            text = firstText,
            fontSize = 24.sp,
            modifier = modifier.padding(16.dp)
        )
        Text(
            text = SecondText,
            textAlign = TextAlign.Justify,
            modifier = modifier.padding(horizontal = 16.dp)
        )
        Text(
            text = thirdText,
            textAlign = TextAlign.Justify,
            modifier = modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    ComposeArticleTheme {
        App("Android","Android","Android")
    }
}