package keyderivation

import org.bouncycastle.crypto.digests.SHA256Digest
import org.bouncycastle.crypto.generators.PKCS5S2ParametersGenerator
import org.bouncycastle.crypto.params.KeyParameter

class PBKDF2KeyDerivationFunction : KeyDerivationFunction {
    private val iterations = 65536
    private val keyLengthBits = 256

    override fun deriveKey(seed: ByteArray, salt: ByteArray): ByteArray {
        val gen = PKCS5S2ParametersGenerator(SHA256Digest())
        gen.init(seed, salt, iterations)
        val keyParameter = gen.generateDerivedParameters(keyLengthBits) as KeyParameter

        return keyParameter.key
    }
}