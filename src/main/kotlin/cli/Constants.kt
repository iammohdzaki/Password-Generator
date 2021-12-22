package cli

object App {
    const val appName = "Password Generator CLI"
    const val version = "0.1"
}

enum class Options(val option: String, val description: String) {
    VERSION("version","Shows Version"),
    PASSWORD_TYPE("type","Password Type"),
    PASSWORD_LENGTH("length","Set Password Length"),
    LOGS("showLogs","Logs"),
    CUSTOM("custom","Custom Password"),
    INCLUDE("include","Include Filters( upper lower digits special )d"),
    ;
}

enum class Filters(val arg: String) {
    UPPERCASE("upper"),
    LOWERCASE("lower"),
    SPECIAL("special"),
    DIGITS("digits")
}
