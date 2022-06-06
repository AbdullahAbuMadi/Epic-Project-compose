package com.abumadi.epicprojectcompose.compose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.lifecycleScope
import com.abumadi.epicprojectcompose.R
import com.abumadi.epicprojectcompose.ui.theme.EpicProjectComposeTheme
import kotlinx.coroutines.delay

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EpicProjectComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
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
        }
        val intent = Intent(this, MainActivity::class.java)
        lifecycleScope.launchWhenStarted {
            delay(2_000)
            startActivity(intent)
        }
    }
}
