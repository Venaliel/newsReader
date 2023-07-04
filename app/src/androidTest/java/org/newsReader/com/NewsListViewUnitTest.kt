package org.newsReader.com

import android.content.Context
import androidx.annotation.ArrayRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.newsReader.com.models.local.News
import org.newsReader.com.models.local.Source
import org.newsReader.com.ui.style.GeneralTheme
import org.newsReader.com.ui.views.newsdetail.NewsDetail
import org.newsReader.com.ui.views.newslist.NewsListView
import java.util.concurrent.TimeUnit

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class NewsListViewUnitTest {



    @get:Rule
    val composeRuleGeneric = createComposeRule()

    @Before
    fun setUp() {
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        composeRuleGeneric.setContent {
            GeneralTheme {
                NewsListView(navController = navController)
            }
        }
    }

    @Test
    fun testLazyColumnPresent() {
        composeRuleGeneric.onNodeWithTag("NewsListView").assertIsDisplayed()
    }

    @Test
    fun testLazyColumnTitlePresent() {
        composeRuleGeneric.onNodeWithTag("TopTitle").assertIsDisplayed()
    }

    @Test
    fun testTitlePresent() {
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
        @ArrayRes val title = context.resources.getString(R.string.news_list_title)
        composeRuleGeneric.onNodeWithText(title).assertIsDisplayed()
    }


    @OptIn(ExperimentalTestApi::class)
    @Test
    fun testListPopulated() {
        composeRuleGeneric.waitUntilAtLeastOneExists(hasTestTag("News"),10000L)
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun testOnClickFirstNews() {
        composeRuleGeneric.waitUntilAtLeastOneExists(hasTestTag("News"),10000L)
        composeRuleGeneric.onAllNodesWithTag("News").onFirst().assertHasClickAction()
    }
}