import keyderivation.ScryptKeyDerivationFunction
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class ScryptKeyDerivationFunctionShould(val testVector: TestVector) {
    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data() : List<TestVector> {
            return loadTestSpec().vectors
        }
    }

    @Test
    fun deriveKeyFromSeedAndSalt() {
        val sut = ScryptKeyDerivationFunction()
        val result = sut.deriveKey(testVector.passphrase.toByteArray(Charsets.UTF_8).plus(0x1), testVector.salt.toByteArray(Charsets.UTF_8).plus(0x1))
        assertThat(result, `is`(testVector.seeds[0].toHexDecodedByteArray()))
    }
}
