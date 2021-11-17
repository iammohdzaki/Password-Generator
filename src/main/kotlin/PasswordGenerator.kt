import data.entity.MetaData
import data.entity.Response
import utils.DEFAULT_PASSWORD_LENGTH
import utils.GeneratorHelper
import utils.PasswordType
import utils.WordsHelper

/**
 * @author Mohammad Zaki
 * [PasswordGenerator] allows you to generate different type of password and different filters.
 * @param builder allows you to choose appropriate options and filters.
 */
open class PasswordGenerator(private var builder: Builder) {

    /**
     * Returns Instance of [PasswordGenerator]
     * @param type as [PasswordType],which determine which type of password you want to generate.
     */
    class Builder(var type: PasswordType) {
        internal var length: Int = DEFAULT_PASSWORD_LENGTH
        internal var includeUpperCase = false
        internal var includeLowerCase = false
        internal var includeSpecialSymbols = false
        internal var includeNumbers = false
        internal var callback: Callback? = null
        internal var showLogs: Boolean = false
        internal var customPassword: String = ""

        /**
         * Password Length allows to you set password length
         * By Default the length is [DEFAULT_PASSWORD_LENGTH]
         * @param length
         */
        fun passwordLength(length: Int): Builder {
            this.length = length
            return this
        }

        /**
         * Include Upper case Characters in password
         * @param value
         * @default false
         */
        fun includeUpperCaseChars(value: Boolean): Builder {
            includeUpperCase = value
            return this
        }

        /**
         * Include Lower Case Characters in password
         * @param value
         * @default false
         */
        fun includeLowerCaseChars(value: Boolean): Builder {
            includeLowerCase = value
            return this
        }

        /**
         * Include Special Characters in password
         * @param value
         * @default false
         */
        fun includeSpecialSymbols(value: Boolean): Builder {
            includeSpecialSymbols = value
            return this
        }

        /**
         * Include Numbers in password
         * @param value
         * @default false
         */
        fun includeNumbers(value: Boolean): Builder {
            includeNumbers = value
            return this
        }

        /**
         * Returns [Callback] after password generation with other details
         * @param callback
         */
        fun callback(callback: Callback): Builder {
            this.callback = callback
            return this
        }

        /**
         * If you want to write test case,Then pass
         * @param value as true,
         * it will show some additional logs to show how password is generating
         */
        fun showLogs(value: Boolean): Builder {
            showLogs = value
            return this
        }

        /**
         * If you want to create password from your custom string,Then pass
         * @param value as String
         */
        fun customPassword(value: String): Builder {
            customPassword = value
            return this
        }

        /**
         * @return [PasswordGenerator] instance
         */
        fun build(): PasswordGenerator {
            return PasswordGenerator(this)
        }
    }

    /**
     * Generates Password According to the type and filters received.
     */
    fun generate() {
        if (builder.showLogs) println("PASSWORD TYPE -> ${builder.type.name}")
        val metaData = MetaData(
            builder.length,
            builder.includeUpperCase,
            builder.includeLowerCase,
            builder.includeSpecialSymbols,
            builder.includeNumbers,
            builder.showLogs,
            builder.customPassword
        )
        when (builder.type) {
            PasswordType.RANDOM -> {
                GeneratorHelper.generateRandomPassword(
                    metaData,
                    builder.callback
                )
            }
            PasswordType.DASHED -> {
                GeneratorHelper.generateDashedPassword(
                    metaData,
                    builder.callback
                )
            }
            PasswordType.MEMORABLE -> {
                //Load Word File in Memory
                WordsHelper.loadWords(false)
                GeneratorHelper.generateMemorablePassword(metaData, builder.callback)
            }
            PasswordType.CUSTOM -> {
                GeneratorHelper.generateCustomPassword(metaData, builder.callback)
            }
        }
    }

    interface Callback {
        fun onPasswordGenerated(response: Response)
    }
}

