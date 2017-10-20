package net.rossharper.koin.keyderivation

interface PublicKeyDerivationFunction {
    fun deriveKey(privateKey: ByteArray): ByteArray
}