package org.newsReader.com

import android.content.Context
import androidx.annotation.ArrayRes
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test
import org.newsReader.com.models.local.News
import org.newsReader.com.models.local.Source
import org.newsReader.com.ui.style.GeneralTheme
import org.newsReader.com.ui.views.newsdetail.NewsDetail
import java.util.concurrent.TimeUnit

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class ExampleUnitTest {



    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()



    @get:Rule
    val composeRuleGeneric = createComposeRule()


    @Test
    fun myTestNavigation() {
        composeRuleGeneric.setContent {
            GeneralTheme {
               MainActivity()
            }
        }
        TimeUnit.SECONDS.sleep(20)
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
        @ArrayRes val title = context.resources.getString(R.string.news_list_title)
        composeRuleGeneric.onNodeWithText(title).assertIsDisplayed()
        //composeTestRule.onNodeWithTag("News").assertExists("Aucun article trouvé")
        composeRuleGeneric.onNodeWithTag("NewsListView").performTouchInput{ swipeUp() }
    }

    @Test
    fun testNewsDisplay() {
        composeRuleGeneric.setContent {
                NewsDetail(News(0,
                    Source("source_id","source_name"),"author", "description", "url", "urlToImage", "https://www.amazingtalker.fr/questions/907",null,"loremp ipsum"))
        }
        composeRuleGeneric.onNodeWithText("description").assertIsDisplayed()

        composeRuleGeneric.onNodeWithText("loremp ipsum").assertIsDisplayed()


        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
        @ArrayRes val buttonText = context.resources.getString(R.string.button_show_article)
        composeRuleGeneric.onNodeWithText(buttonText).assertHasClickAction()
    }

}