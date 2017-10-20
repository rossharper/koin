package keyderivation

import keyderivation.PublicKeyDerivationFunction
import org.bouncycastle.jce.ECNamedCurveTable
import java.math.BigInteger
import java.security.MessageDigest

class BitcoinPublicKeyDerivationFunction : PublicKeyDerivationFunction {
    override fun deriveKey(privateKey: ByteArray): ByteArray {
        // Calculate public key point on elliptic curve
        val params = ECNamedCurveTable.getParameterSpec("secp256k1")
        val pubPoint = params.g.multiply(BigInteger(1, privateKey))
        // Encode the public key point
        val encodedPubPoint = pubPoint.getEncoded(false)

        // Hash public key point

        // SHA256 Hash
        val pubKeySHA = MessageDigest.getInstance("SHA-256").digest(encodedPubPoint)

        // RIPEMD160 hash the sha hash
        val ripeDigest = MessageDigest.getInstance("ripemd160")

        return ripeDigest.digest(pubKeySHA)
    }

}