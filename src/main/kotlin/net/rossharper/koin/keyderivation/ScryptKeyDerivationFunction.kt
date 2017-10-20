package net.rossharper.koin.keyderivation

import org.bouncycastle.crypto.generators.SCrypt

class ScryptKeyDerivationFunction : KeyDerivationFunction {
    private val iterations = Math.pow(2.0, 18.0).toInt()
    private val keyLengthBytes = 32
    private val r = 8
    private val p = 1

    override fun deriveKey(seed: ByteArray, salt: ByteArray): ByteArray {
        return SCrypt.generate(seed, salt, iterations, r, p, keyLengthBytes)
    }
}