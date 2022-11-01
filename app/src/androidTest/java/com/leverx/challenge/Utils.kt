package com.leverx.challenge

import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsNodeInteraction
import org.junit.Assert

/*
 * Testing utilities.
 */

/**
 * Asserts that [SemanticsProperties.EditableText]'s value is equal to [expected] one.
 *
 * @throws AssertionError if values are not equal.
 */
fun SemanticsNodeInteraction.assertEditableTextEquals(expected: String) {
    val config = fetchSemanticsNode().config
    val editable = config.find { it.key == SemanticsProperties.EditableText }
    val actual = editable?.value?.toString()
    Assert.assertEquals(expected, requireNotNull(actual))
}