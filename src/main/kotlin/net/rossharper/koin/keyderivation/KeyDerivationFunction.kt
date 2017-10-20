package net.rossharper.koin.keyderivation

interface KeyDerivationFunction {
    fun deriveKey(seed: ByteArray, salt: ByteArray): ByteArray
}