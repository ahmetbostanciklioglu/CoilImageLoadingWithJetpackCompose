package com.ahmet.bostanciklioglu.coilimageloadinglibrarywithjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.ahmet.bostanciklioglu.coilimageloadinglibrarywithjetpackcompose.ui.theme.CoilImageLoadingLibraryWithJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoilImageLoadingLibraryWithJetpackComposeTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CoilImage()
                }
            }
        }
    }
}

@Composable
fun CoilImage() {
    Box(modifier = Modifier.size(150.dp), contentAlignment = Alignment.Center) {

        val url = "Paste here your special Image URL"
        val painter =
            rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current)
                    .data(data = url)
                    .crossfade(1000)
                    .error(R.drawable.ic_error)
                    .placeholder(R.drawable.ic_placeholder)
                    .transformations(
                        RoundedCornersTransformation()
                    )
                    .build()
            )

        val painterState = painter.state
        Image(
            painter = painter,
            contentDescription = "logo Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(192.dp)
                .background(color = Red)
        )
        if (painterState is AsyncImagePainter.State.Loading) {
            CircularProgressIndicator()
        }
    }
}