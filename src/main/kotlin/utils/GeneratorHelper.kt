package utils

import com.nulabinc.zxcvbn.Strength
import com.nulabinc.zxcvbn.Zxcvbn
import data.entity.Generation
import data.entity.Response
import data.entity.StrengthResult
import data.response.Callback

/**
Created by Mohammad Zaki
on Nov,13 2021
 **/
object GeneratorHelper {

    fun generateRandomPassword(generation: Generation, callback: Callback?) {
        val filter = generation.getList()
        if (generation.isTestCase) {
            println("GENERATING RANDOM PASSWORD")
            println("FILTER CHOSEN : -> $filter")
        }
        var password = ""
        if (filter.size > 0) {
            for (c in 0 until generation.length) {
                println("COUNT : -> $c")
                when (filter.random()) {
                    FilterValues.UPPER_CASE -> password += ('A'..'Z').random().toString()
                    FilterValues.LOWER_CASE -> password += ('a'..'z').random().toString()
                    FilterValues.SPECIAL_SYMBOLS -> SPECIAL_SYMBOLS.random().toString()
                    FilterValues.NUMBERS -> password += (0..9).random().toString()
                }
            }
        }
        if (generation.isTestCase) println("GENERATED PASSWORD -> $password")
        val strength = checkPasswordStrength(password, generation.isTestCase)
        callback?.onPasswordGenerated(
            Response(
                password = password
            )
        )
    }

    private fun checkPasswordStrength(password: String, isTestCase: Boolean): Strength {
        val strength = Zxcvbn().measure(password)
        val strengthResult = StrengthResult(
            strength.crackTimesDisplay.toString()
        )
        if (isTestCase) {
            println("STRENGTH CHECK -> $password : This password will take ${strengthResult.crackTimeDisplay} to crack!")
            println("STRENGTH RATING : ${strength.score}/10")
            println("VERBAL_FEEDBACK : ${strength.feedback}")
            println("TIME TAKEN : ${strength.calcTime}")
            println("SEQUENCE : ${strength.sequence}")
            println("GUESSED NEEDED : ${strength.guesses}")
            println("CRACK IN SECONDS : ${strength.crackTimeSeconds}")
        }
        return strength
    }

}