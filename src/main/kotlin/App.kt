import cli.App
import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import kotlinx.cli.default

fun main(args: Array<String>) {
    val parser = ArgParser("${App.appName}:: ${App.version}")
    val version by parser.option(ArgType.Boolean, shortName = "v", description = "Version").default(false)

    parser.parse(args)

    if(version) println(App.version)
}
