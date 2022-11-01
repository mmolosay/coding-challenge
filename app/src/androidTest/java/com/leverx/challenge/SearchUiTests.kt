package com.leverx.challenge

import androidx.activity.ComponentActivity
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.leverx.challenge.ui.components.home.Search
import com.leverx.challenge.ui.components.home.TestTags
import com.leverx.challenge.ui.environment.AppTheme
import com.leverx.challenge.ui.environment.composition.AppPreviewCompositionLocalProvider
import com.leverx.challenge.viewmodel.SearchViewModel
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Tests for [Search] UI component.
 */
@RunWith(AndroidJUnit4::class)
class SearchUiTests {

    @get:Rule
//    val composeRule = createComposeRule()
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    /**
     * Tests, that text field's trailing "search" icon enables,
     * when something is being entered into text field.
     */
    @Test
    fun test_search_icon_enables_when_filling_text_field() {
        composeRule.setContent {
            AppTheme {
                AppPreviewCompositionLocalProvider {
                    Search(
                        uiState = SearchViewModel.UiState.Blank,
                        onSearchClick = {},
                        markImageAsViewed = {},
                    )
                }
            }
        }

        val textField = composeRule.onNodeWithTag(TestTags.SearchField)
        val searchIcon = composeRule.onNodeWithTag(TestTags.SearchFieldTrailingIcon)

        val userInput = "a"

        textField.assertEditableTextEquals("") // empty at start
        searchIcon.assertIsNotEnabled() // disabled for empty input

        textField.performTextInput(userInput) // simulate beginning of user's text input

        searchIcon.assertIsEnabled() // now, with some text in text field, icon is enabled
        textField.assertEditableTextEquals(userInput) // and text is acually updated
    }
}