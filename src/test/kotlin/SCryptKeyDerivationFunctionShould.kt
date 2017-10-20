import keyderivation.SCryptKeyDerivationFunction
import org.bouncycastle.util.encoders.Hex
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class SCryptKeyDerivationFunctionShould(val testVector: TestVector) {
    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data() : List<TestVector> {
            return loadTestSpec().vectors
        }
    }

    @Test
    fun deriveKeyFromSeedAndSalt() {
        val sut = SCryptKeyDerivationFunction()
        val result = sut.deriveKey(testVector.passphrase.toByteArray(Charsets.UTF_8).plus(0x1), testVector.salt.toByteArray(Charsets.UTF_8).plus(0x1))
        MatcherAssert.assertThat(result, Matchers.`is`(testVector.seeds[0].toHexDecodedByteArray()))
    }
}

private fun String.toHexDecodedByteArray(): ByteArray = Hex.decode(this)
