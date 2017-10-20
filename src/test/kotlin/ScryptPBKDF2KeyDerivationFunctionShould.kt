import net.rossharper.koin.keyderivation.ScryptPBKDF2KeyDerivationFunction
import org.hamcrest.Matchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class ScryptPBKDF2KeyDerivationFunctionShould(val testVector: TestVector) {
    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data() : List<TestVector> {
            return loadTestSpec().vectors
        }
    }

    @Test
    fun deriveKey() {
        val sut = ScryptPBKDF2KeyDerivationFunction()
        val result = sut.deriveKey(testVector.passphrase.toByteArray(Charsets.UTF_8), testVector.salt.toByteArray(Charsets.UTF_8))
        assertThat(result, `is`(testVector.seeds[2].toHexDecodedByteArray()))
    }
}