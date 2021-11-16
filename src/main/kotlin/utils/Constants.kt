package utils

/**
Created by Mohammad Zaki
on Nov,13 2021
 **/

const val DEFAULT_PASSWORD_LENGTH = 8
const val WORDS_START_KEY = 11111
const val WORDS_END_KEY = 66666

/**
 * Password Type
 * @property RANDOM - This will generate random password based on filters.
 * @property MEMORABLE - This will generate password which is easy to memorise based on your filters.
 * @property DASHED - This will generate a dashed password based on your filters.
 *  * (NOTE): You can't set length of password in [MEMORABLE] and [DASHED] type of password.
 */
enum class PasswordType {
    RANDOM,
    MEMORABLE,
    DASHED
}

object FilterValues {
    const val UPPER_CASE = 0
    const val LOWER_CASE = 1
    const val SPECIAL_SYMBOLS = 2
    const val NUMBERS = 3
}

val SPECIAL_SYMBOLS = listOf('!', '@', '#', '$', '%', '&', '*', '+', '=', '-', '~', '?', '/', '_')

val REPLACEABLE_NUMBER_CHARS = HashMap<Char, Char>()
    .apply {
        put('O', '0')
        put('o', '0')
        put('E', '3')
        put('e', '3')
    }

val REPLACEABLE_SPECIAL_CHARS = HashMap<Char, Char>()
    .apply {
        put('A', '@')
        put('H', '#')
        put('I', '!')
        put('O', '*')
        put('a', '@')
        put('o', '*')
        put('h', '#')
        put('i', '!')
    }

val DASHED_LENGTHS = arrayOf(8, 10, 12, 14, 16)
val DASHED_PAIRS = arrayOf(2, 3, 4)