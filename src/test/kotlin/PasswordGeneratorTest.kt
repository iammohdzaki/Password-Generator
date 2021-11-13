import com.google.common.truth.Truth.assertThat
import data.entity.Response
import data.response.Callback
import org.junit.Test
import utils.PasswordType

class PasswordGeneratorTest {

    @Test
    fun generateRandomPasswordWithAllFilters() {
        PasswordGenerator.Builder(PasswordType.RANDOM)
            .isTestCase(true)
            .includeUpperCaseChars(true)
            .includeSpecialSymbols(true)
            .includeLowerCaseChars(true)
            .includeNumbers(true)
            .callback(object : Callback {
                override fun onPasswordGenerated(response: Response) {
                    val pass = response.password
                    assertThat(pass.length).isEqualTo(8)
                }
            })
            .build()
            .generate()
    }

}