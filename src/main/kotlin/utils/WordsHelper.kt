package utils

import java.io.File
import java.util.*
import kotlin.collections.HashMap

internal object WordsHelper {

    private val wordDict = HashMap<Int, String>()

    /**
     * Load Predefined Words from Text File
     */
    fun loadWords(showLogs: Boolean) {
        if (wordDict.size <= 0) {
            File("src/main/resources/words.txt").readLines()
                .forEach {
                    val value = it.split("\t")
                    wordDict[value[0].toInt()] = value[1]
                }
            //if (showLogs) println("WORD MAP ----->\n$wordDict   ${wordDict[1]}")
        }

    }

    /**
     * Returns a word [WORDS_START_KEY] -- [WORDS_END_KEY]
     */
    fun getRandomWord() = wordDict.entries.elementAt(Random().nextInt(wordDict.size)).value

}