package utils

/**
Created by Mohammad Zaki
on Nov,13 2021
 **/

const val DEFAULT_PASSWORD_LENGTH = 8

val SPECIAL_SYMBOLS = listOf('!','@','#','$','%','&','*','+','=','-','~','?','/','_')

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

