package utils

import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.util.*


internal object WordsHelper {

    private val wordDict = HashMap<Int, String>()

    /**
     * Load Predefined Words from Text File
     */
    fun loadWords(showLogs: Boolean) {
        if (wordDict.size <= 0) {
            val `in` = javaClass.getResourceAsStream("/words.txt")
            val reader = BufferedReader(InputStreamReader(`in`))
            reader.lines().forEach {
                val value = it.split("\t")
                wordDict[value[0].toInt()] = value[1]
            }
            if (showLogs) println("WORD MAP ----->\n$wordDict   ${wordDict[1]}")
        }

    }

    /**
     * Returns a word [WORDS_START_KEY] -- [WORDS_END_KEY]
     */
    fun getRandomWord() = wordDict.entries.elementAt(Random().nextInt(wordDict.size)).value

}