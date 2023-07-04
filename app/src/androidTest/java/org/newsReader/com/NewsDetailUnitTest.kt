package org.newsReader.com

import android.content.Context
import androidx.annotation.ArrayRes
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.newsReader.com.models.local.News
import org.newsReader.com.models.local.Source
import org.newsReader.com.ui.style.GeneralTheme
import org.newsReader.com.ui.views.newsdetail.NewsDetail

/**
 * Example NewsDetailUnitTest, which will execute on the emulator from developpement machine (host).
 **/

class NewsDetailUnitTest {



    @get:Rule
    val composeRuleGeneric = createComposeRule()

    @Before
    fun setUp() {
        composeRuleGeneric.setContent {
            GeneralTheme {
                NewsDetail(News("id_string",
                    Source("source_id","source_name"),"author", "title", "url", "urlToImage", "https://www.amazingtalker.fr/questions/907",null,"loremp ipsum"))
            }
        }
    }

    @Test
    fun testImageIsPresent() {
        composeRuleGeneric.onAllNodesWithTag("NewsDetailImage").onFirst().assertIsDisplayed()
    }

    @Test
    fun testContentIsPresent() {
        composeRuleGeneric.onNodeWithText("loremp ipsum").assertIsDisplayed()
    }

    @Test
    fun testTitleIsPresent() {
        composeRuleGeneric.onNodeWithText("title").assertIsDisplayed()
    }


    @Test
    fun testButtonIsPresent() {
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
        @ArrayRes val buttonText = context.resources.getString(R.string.button_show_article)
        composeRuleGeneric.onNodeWithText(buttonText).assertHasClickAction()
    }

}