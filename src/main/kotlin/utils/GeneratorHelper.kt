package utils

import com.nulabinc.zxcvbn.Zxcvbn
import data.entity.Feedback
import data.entity.MetaData
import data.entity.Response
import data.entity.StrengthResult
import data.response.Callback

/**
Created by Mohammad Zaki
on Nov,13 2021
 **/
internal object GeneratorHelper {

    /**
     * Generates Random Type Password According to Filters
     * @param metaData contains filters
     * @param callback returns Callback after password generation
     */
    fun generateRandomPassword(metaData: MetaData, callback: Callback?) {
        val filter = metaData.getList()
        if (metaData.showLogs) println("GENERATING RANDOM PASSWORD")
        var password = ""
        if (filter.size > 0) {
            for (c in 0 until metaData.length) {
                if(metaData.onlyUpperCase()){
                    password += ('A'..'Z').random().toString()
                    continue
                }
                if(metaData.onlyLowerCase()){
                    password += ('a'..'z').random().toString()
                    continue
                }
                if(metaData.onlySpecialChars()){
                    password += SPECIAL_SYMBOLS.random().toString()
                    continue
                }
                if(metaData.onlyNumbers()){
                    password += (0..9).random().toString()
                    continue
                }

                when (filter.random()) {
                    FilterValues.UPPER_CASE -> password += ('A'..'Z').random().toString()
                    FilterValues.LOWER_CASE -> password += ('a'..'z').random().toString()
                    FilterValues.SPECIAL_SYMBOLS -> password += SPECIAL_SYMBOLS.random().toString()
                    FilterValues.NUMBERS -> password += (0..9).random().toString()
                }
            }
        }
        if (metaData.showLogs) println("GENERATED PASSWORD -> $password")
        val strength = checkPasswordStrength(password, metaData.showLogs)
        callback?.onPasswordGenerated(
            Response(
                password = password,
                strengthResult = strength
            )
        )
    }

    /**
     * Generates Memorable Type Password According to Filters
     * @param metaData contains filters
     * @param callback returns Callback after password generation
     */
    fun generateMemorablePassword(metaData: MetaData, callback: Callback?) {
        if (metaData.showLogs) println("GENERATING MEMORABLE PASSWORD")
        var word = WordsHelper.getRandomWord()
        if (metaData.showLogs) println("Word -> $word")

        when {
            metaData.onlyUpperCase() -> {
                word = word.uppercase()
            }
            metaData.onlyLowerCase() -> {
                word = word.lowercase()
            }
            metaData.onlySpecialChars() -> {
                word = replaceChars(word, REPLACEABLE_SPECIAL_CHARS)
            }
            metaData.onlyNumbers() -> {
                word = replaceChars(word, REPLACEABLE_NUMBER_CHARS)
            }
            else -> {
                if (metaData.upperCase) {
                    val times = word.length / (1 until word.length).random()
                    for (t in 0 until times) {
                        val ranPos = (word.indices).random()
                        val w = word[ranPos].uppercase()
                        word = word.replace(word[ranPos].toString(), w)
                    }
                } else {
                    word = word.lowercase()
                }

                if (metaData.lowerCase) {
                    val times = word.length / (1 until word.length).random()
                    for (t in 0 until times) {
                        val ranPos = (word.indices).random()
                        val w = word[ranPos].lowercase()
                        word = word.replace(word[ranPos].toString(), w)
                    }
                } else {
                    word = word.uppercase()
                }

                if (metaData.numbers) {
                    word = replaceChars(word, REPLACEABLE_NUMBER_CHARS)
                    val numberLength = (1..4).random()
                    var gDigits = ""
                    for (d in 0 until numberLength) {
                        gDigits += (0..9).random().toString()
                    }
                    word += gDigits
                }

                if (metaData.specialChars) {
                    word = replaceChars(word, REPLACEABLE_SPECIAL_CHARS)
                    var gChars = ""
                    for (s in 0 until 2) {
                        gChars += SPECIAL_SYMBOLS.random()
                    }
                    word += gChars
                }
            }
        }
        if (metaData.showLogs) println("GENERATED PASSWORD -> $word")
        val strength = checkPasswordStrength(word, metaData.showLogs)
        callback?.onPasswordGenerated(
            Response(
                password = word,
                strengthResult = strength
            )
        )
    }

    /**
     * Replace Characters by passing a map of chars
     */
    private fun replaceChars(word: String, values: HashMap<Char, Char>): String {
        var wordString = word
        wordString.forEach {
            if (values.containsKey(it)) {
                wordString = wordString.replace(it, values[it]!!)
            }
        }
        return wordString
    }

    /**
     * Checks Password Strength and Other Details
     * @param password as Generated Password
     * @param showLogs show logs
     * @return [StrengthResult]
     */
    private fun checkPasswordStrength(password: String, showLogs: Boolean): StrengthResult {
        if (password.isEmpty()) {
            return StrengthResult()
        }
        val strength = Zxcvbn().measure(password)
        if (showLogs) {
            println("STRENGTH CHECK -> ")
            println("onlineThrottling100perHour : ${strength.crackTimesDisplay.onlineThrottling100perHour}")
            println("offlineFastHashing1e10PerSecond : ${strength.crackTimesDisplay.offlineFastHashing1e10PerSecond}")
            println("onlineNoThrottling10perSecond : ${strength.crackTimesDisplay.onlineNoThrottling10perSecond}")
            println("offlineSlowHashing1e4perSecond : ${strength.crackTimesDisplay.offlineSlowHashing1e4perSecond}")
            println("STRENGTH RATING : ${strength.score}/10")
            println("VERBAL_FEEDBACK : ${strength.feedback.suggestions}")
            println("TIME TAKEN : ${strength.calcTime}")
            println("SEQUENCE : ${strength.sequence}")
            println("GUESSED NEEDED : ${strength.guessesLog10}")
            println("CRACK IN SECONDS : ${strength.crackTimeSeconds.onlineThrottling100perHour}")
        }
        return StrengthResult(
            strength.crackTimesDisplay.offlineSlowHashing1e4perSecond,
            strength.score,
            Feedback(
                strength.feedback.warning,
                strength.feedback.suggestions.toList(),
            ),
            strength.guesses.toInt(),
            strength.crackTimeSeconds.offlineSlowHashing1e4perSecond.toInt()
        )
    }

}