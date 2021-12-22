import cli.App
import cli.Filters
import cli.Options
import data.entity.Response
import kotlinx.cli.*
import utils.PasswordType

@ExperimentalCli
fun main(args: Array<String>) {
    val parser = ArgParser("${App.appName}:: ${App.version}")
    val version by parser.option(
        ArgType.Boolean,
        shortName = Options.VERSION.option,
        description = Options.VERSION.description
    ).default(false)

    val passwordType by parser.option(
        ArgType.Choice<PasswordType>(),
        shortName = Options.PASSWORD_TYPE.option,
        description = Options.PASSWORD_TYPE.description
    ).default(PasswordType.RANDOM)

    val passwordLength by parser.option(
        ArgType.Int,
        shortName = Options.PASSWORD_LENGTH.option,
        description = Options.PASSWORD_LENGTH.description
    ).default(8)

    val logs by parser.option(ArgType.Boolean, shortName = Options.LOGS.option, description = Options.LOGS.description)
        .default(false)

    val custom by parser.option(
        ArgType.String,
        shortName = Options.CUSTOM.option,
        description = Options.CUSTOM.description
    ).default("GENERATION")

    val filters = ArrayList<Filters>()

    class Filter : Subcommand(Options.INCLUDE.option, Options.INCLUDE.description) {
        val numbers by argument(ArgType.String, description = Options.INCLUDE.description).vararg()
        override fun execute() {
            numbers.forEach {
                when (it) {
                    Filters.UPPERCASE.arg -> filters.add(Filters.UPPERCASE)
                    Filters.LOWERCASE.arg -> filters.add(Filters.LOWERCASE)
                    Filters.DIGITS.arg -> filters.add(Filters.DIGITS)
                    Filters.SPECIAL.arg -> filters.add(Filters.SPECIAL)
                }
            }
        }
    }

    parser.subcommands(Filter())
    parser.parse(args)

    if (version) println(App.version)
    else {
        PasswordGenerator.Builder(passwordType)
            .showLogs(logs)
            .includeUpperCaseChars(filters.contains(Filters.UPPERCASE))
            .includeNumbers(filters.contains(Filters.DIGITS))
            .includeLowerCaseChars(filters.contains(Filters.LOWERCASE))
            .includeSpecialSymbols(filters.contains(Filters.SPECIAL))
            .customPassword(custom)
            .passwordLength(passwordLength)
            .callback(object : PasswordGenerator.Callback {
                override fun onPasswordGenerated(response: Response) {
                    println("\n${App.appName} (${App.version})")
                    println(response.password)
                    println("Password Generated Successfully!\n")
                }
            })
            .build()
            .generate()
    }


}
