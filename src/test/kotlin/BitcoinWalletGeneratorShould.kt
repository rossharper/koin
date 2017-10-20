import org.junit.Test
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import net.rossharper.koin.usecases.createBitcoinWalletGenerator

@RunWith(Parameterized::class)
class BitcoinWalletGeneratorShould (val testVector: TestVector) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data() : List<TestVector> {
            return loadTestSpec().vectors
        }
    }

    @Test
    fun generatePrivateKeyFromSeedPhrase() {
        val sut = createBitcoinWalletGenerator()
        val result = sut.generate(testVector.passphrase, testVector.salt)
        assertThat(result.privateKey, `is`(testVector.keys.private))
    }

    @Test
    fun generateAddressFromSeedPhrase() {
        val sut = createBitcoinWalletGenerator()
        val result = sut.generate(testVector.passphrase, testVector.salt)
        assertThat(result.publicKey, `is`(testVector.keys.public))
    }
}
