package data.entity

import utils.DEFAULT_PASSWORD_LENGTH
import utils.FilterValues

/**
Created by Mohammad Zaki
on Nov,13 2021
 **/
data class MetaData(
    var length: Int = DEFAULT_PASSWORD_LENGTH,
    var upperCase: Boolean = false,
    var lowerCase: Boolean = false,
    var specialChars: Boolean = false,
    var numbers: Boolean = false,
    var showLogs: Boolean = false,
    var customPassword: String = ""
) {
    fun getList(): ArrayList<Int> {
        val list = ArrayList<Int>()
        if (upperCase) list.add(FilterValues.UPPER_CASE)
        if (lowerCase) list.add(FilterValues.LOWER_CASE)
        if (specialChars) list.add(FilterValues.SPECIAL_SYMBOLS)
        if (numbers) list.add(FilterValues.NUMBERS)
        return list
    }

    fun onlyUpperCase() = upperCase && !lowerCase && !specialChars && !numbers
    fun onlyLowerCase() = lowerCase && !upperCase && !specialChars && !numbers
    fun onlySpecialChars() = specialChars && !upperCase && !lowerCase && !numbers
    fun onlyNumbers() = numbers && !upperCase && !lowerCase && !specialChars
}
