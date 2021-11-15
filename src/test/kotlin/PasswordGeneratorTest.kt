import com.google.common.truth.Truth.assertThat
import data.entity.Response
import data.response.Callback
import org.junit.Test
import utils.PasswordType
import utils.WordsHelper

class PasswordGeneratorTest {

    @Test
    fun `generate password with all filters`() {
        PasswordGenerator.Builder(PasswordType.RANDOM)
            .showLogs(true)
            .includeUpperCaseChars(true)
            .includeNumbers(true)
            .includeLowerCaseChars(true)
            .includeSpecialSymbols(true)
            .passwordLength(25)
            .callback(object : Callback {
                override fun onPasswordGenerated(response: Response) {
                    val pass = response.password
                    assertThat(pass.length).isEqualTo(25)
                }
            })
            .build()
            .generate()
    }

    @Test
    fun `generate memorable password with all filters`() {
        PasswordGenerator.Builder(PasswordType.MEMORABLE)
            .showLogs(true)
            .includeLowerCaseChars(true)
            .includeSpecialSymbols(true)
            .includeUpperCaseChars(true)
            .includeNumbers(true)
            .callback(object : Callback {
                override fun onPasswordGenerated(response: Response) {
                    val pass = response.password
                    assertThat(pass).isNotEmpty()
                }
            })
            .build()
            .generate()
    }


    @Test
    fun `memorable password file load check`() {
        WordsHelper.loadWords(true)
    }

}