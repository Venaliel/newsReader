package org.newsReader.com

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import org.newsReader.com.ui.views.newsdetail.NewsDetailView
import org.newsReader.com.ui.views.newslist.NewsListView
import org.newsReader.com.utils.constants.NavigationConstants


@RequiresApi(Build.VERSION_CODES.S)
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun NavigationGraph(
    navController: NavHostController
) {
    val viewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current) {
        "No ViewModelStoreOwner was provided via LocalViewModelStoreOwner"
    }


    AnimatedNavHost(navController, startDestination = NavigationConstants.NEWS_LIST) {

        /** NEWS LIST VIEW **/
        composable(NavigationConstants.NEWS_LIST) {
            CompositionLocalProvider(
                LocalViewModelStoreOwner provides viewModelStoreOwner
            ) {
                NewsListView(navController)
            }
        }

        /** NEWS DETAIL VIEW **/
        composable(NavigationConstants.NEWS_DETAIL) {
            //we don't need arguments here as we are using a shared viewmodel,
            // and we don't save any data or do we search for any specific articles details
            CompositionLocalProvider(
                LocalViewModelStoreOwner provides viewModelStoreOwner
            ) {
                NewsDetailView(
                    navController
                )
            }
        }
    }
}

