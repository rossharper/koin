package keyderivation

interface KeyDerivationFunction {
    fun deriveKey(seed: ByteArray, salt: ByteArray): ByteArray
}