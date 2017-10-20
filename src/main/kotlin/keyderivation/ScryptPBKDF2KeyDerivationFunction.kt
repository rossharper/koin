package keyderivation

import util.xor

class ScryptPBKDF2KeyDerivationFunction : KeyDerivationFunction {
    override fun deriveKey(seed: ByteArray, salt: ByteArray): ByteArray {
        // Scrypt derivation
        val s1 = ScryptKeyDerivationFunction().deriveKey(seed.plus(0x1), salt.plus(0x1))
        // PBKDF2 derivation
        val s2 = PBKDF2KeyDerivationFunction().deriveKey(seed.plus(0x2), salt.plus(0x2))
        // XOR derived keys
        return s1 xor s2
    }
}