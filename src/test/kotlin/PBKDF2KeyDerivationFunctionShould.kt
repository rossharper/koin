import keyderivation.PBKDF2KeyDerivationFunction
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class PBKDF2KeyDerivationFunctionShould(val testVector: TestVector) {
    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data() : List<TestVector> {
            return loadTestSpec().vectors
        }
    }

    @Test
    fun deriveKeyFromSeedAndSalt() {
        val sut = PBKDF2KeyDerivationFunction()
        val result = sut.deriveKey(testVector.passphrase.toByteArray(Charsets.UTF_8).plus(0x2), testVector.salt.toByteArray(Charsets.UTF_8).plus(0x2))
        MatcherAssert.assertThat(result, Matchers.`is`(testVector.seeds[1].toHexDecodedByteArray()))
    }
}