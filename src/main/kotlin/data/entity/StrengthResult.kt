package data.entity

/**
Created by Mohammad Zaki
on Nov,13 2021
 **/
data class StrengthResult(
    var crackTimeDisplay : String = "",
    var passwordRating : Int = 0,
    var feedback: Feedback,
    var guessesNeeded : Int = 0,
    var crackTimeInSec : Int = 0
)

data class Feedback(
    var warning : String = "",
    var suggestions : List<String> = ArrayList()
)
