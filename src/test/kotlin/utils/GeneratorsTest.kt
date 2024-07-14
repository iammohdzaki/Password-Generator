package utils

import com.google.common.truth.Truth.assertThat
import kotlin.test.Test

class GeneratorsTest {

    @Test
    fun generateUniqueKey() {
        val length = 10
        val key = Generators.generateUniqueKey(length = length, "user_id", "z@yopmail.com")
        println(key)
        assertThat(key.length).isEqualTo(length)
    }
}