package com.abumadi.epicprojectcompose.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.abumadi.epicprojectcompose.R
import com.abumadi.epicprojectcompose.ui.theme.EpicProjectComposeTheme

@Composable
fun SplashScreen(
    vectorPainter: Painter,
    iconPainter: Painter,
    backgroundColor: Color,
    vectorContentDescription: String,
    iconContentDescription: String,
) {
    Column(
        Modifier
            .background(color = backgroundColor)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        Box(contentAlignment = Alignment.Center) {
            Image(
                painter = vectorPainter,
                contentDescription = vectorContentDescription,
                Modifier.fillMaxSize()
            )
            Image(
                painter = iconPainter,
                contentDescription = iconContentDescription, Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashDefaultPreview() {
    EpicProjectComposeTheme {
        SplashScreen(
            backgroundColor = colorResource(id = R.color.AppColor),
            iconContentDescription = "epic icon",
            iconPainter = painterResource(
                id = R.drawable.ic_epic_icon
            ),
            vectorContentDescription = "splash vector",
            vectorPainter = painterResource(id = R.drawable.ic_vector)
        )
    }
}