package keyderivation

interface PublicKeyDerivationFunction {
    fun deriveKey(privateKey: ByteArray): ByteArray
}