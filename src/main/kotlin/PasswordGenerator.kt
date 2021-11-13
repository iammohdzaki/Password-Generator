import data.entity.Generation
import data.response.Callback
import utils.DEFAULT_PASSWORD_LENGTH
import utils.GeneratorHelper
import utils.PasswordType

class PasswordGenerator(private var builder: Builder) {

    class Builder(var type: PasswordType) {
        var length: Int = DEFAULT_PASSWORD_LENGTH
        var includeUpperCase = false
        var includeLowerCase = false
        var includeSpecialSymbols = false
        var includeNumbers = false
        var callback: Callback? = null
        var isTestCase: Boolean = false

        fun passwordLength(length: Int): Builder {
            this.length = length
            return this
        }

        fun includeUpperCaseChars(value: Boolean): Builder {
            includeUpperCase = value
            return this
        }

        fun includeLowerCaseChars(value: Boolean): Builder {
            includeLowerCase = value
            return this
        }

        fun includeSpecialSymbols(value: Boolean): Builder {
            includeSpecialSymbols = value
            return this
        }

        fun includeNumbers(value: Boolean): Builder {
            includeNumbers = value
            return this
        }

        fun callback(callback: Callback): Builder {
            this.callback = callback
            return this
        }

        fun isTestCase(value: Boolean): Builder {
            this.isTestCase = value
            return this
        }

        fun build(): PasswordGenerator {
            return PasswordGenerator(this)
        }
    }

    fun generate() {
        if (builder.isTestCase) println("PASSWORD TYPE -> ${builder.type.name}")
        when (builder.type) {
            PasswordType.RANDOM -> {
                GeneratorHelper.generateRandomPassword(
                    Generation(
                        builder.length,
                        builder.includeUpperCase,
                        builder.includeLowerCase,
                        builder.includeSpecialSymbols,
                        builder.includeNumbers,
                        builder.isTestCase
                    ),
                    builder.callback
                )
            }
            PasswordType.DASHED -> {

            }
            PasswordType.MEMORABLE -> {

            }
        }
    }
}