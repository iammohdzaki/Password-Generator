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
object GeneratorHelper {

    fun generateRandomPassword(metaData: MetaData, callback: Callback?) {
        val filter = metaData.getList()
        if (metaData.isTestCase) println("GENERATING RANDOM PASSWORD")
        var password = ""
        if (filter.size > 0) {
            for (c in 0 until metaData.length) {
                when (filter.random()) {
                    FilterValues.UPPER_CASE -> password += ('A'..'Z').random().toString()
                    FilterValues.LOWER_CASE -> password += ('a'..'z').random().toString()
                    FilterValues.SPECIAL_SYMBOLS -> password += SPECIAL_SYMBOLS.random().toString()
                    FilterValues.NUMBERS -> password += (0..9).random().toString()
                }
            }
        }
        if (metaData.isTestCase) println("GENERATED PASSWORD -> $password")
        val strength = checkPasswordStrength(password, metaData.isTestCase)
        callback?.onPasswordGenerated(
            Response(
                password = password,
                strengthResult = strength
            )
        )
    }

    private fun checkPasswordStrength(password: String, isTestCase: Boolean): StrengthResult {
        val strength = Zxcvbn().measure(password)
        if (isTestCase) {
            println("STRENGTH CHECK -> $password : This password will take ${strength.crackTimesDisplay.onlineThrottling100perHour} to crack!")
            println("STRENGTH RATING : ${strength.score}/10")
            println("VERBAL_FEEDBACK : ${strength.feedback.suggestions}")
            println("TIME TAKEN : ${strength.calcTime}")
            println("SEQUENCE : ${strength.sequence}")
            println("GUESSED NEEDED : ${strength.guessesLog10}")
            println("CRACK IN SECONDS : ${strength.crackTimeSeconds.onlineThrottling100perHour}")
        }
        return StrengthResult(
            strength.crackTimesDisplay.onlineThrottling100perHour,
            strength.score,
            Feedback(
                strength.feedback.warning,
                strength.feedback.suggestions.toList(),
            ),
            strength.guesses.toInt(),
            strength.crackTimeSeconds.onlineThrottling100perHour.toInt()
        )
    }

}