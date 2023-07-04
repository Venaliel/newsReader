package org.newsReader.com


import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.newsReader.com.ui.style.GeneralTheme
import java.util.*


class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.S)
    @ExperimentalUnitApi
    @ExperimentalFoundationApi
    @ExperimentalMaterialApi
    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            val systemUiController = rememberSystemUiController()
            systemUiController.setStatusBarColor(color = Color.Transparent, darkIcons = true)
            GeneralTheme {
                MainScreenView()
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.S)
@ExperimentalUnitApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun MainScreenView() {
        val navController = rememberNavController()
        Box {
            Scaffold(
                backgroundColor = Color.Transparent,
            ) { innerPadding ->

                Box(
                    modifier = Modifier.padding(innerPadding),
                    contentAlignment = Alignment.BottomEnd,
                ) {
                    NavigationGraph(
                        navController = navController
                    )
                }
            }
    }
}