package net.rossharper.koin.usecases

import net.rossharper.koin.keyderivation.BitcoinPublicKeyDerivationFunction
import net.rossharper.koin.keyderivation.KeyDerivationFunction
import net.rossharper.koin.keyderivation.PublicKeyDerivationFunction
import net.rossharper.koin.keyderivation.ScryptPBKDF2KeyDerivationFunction
import net.rossharper.koin.model.BitcoinWallet
import net.rossharper.koin.model.PrivateKey
import net.rossharper.koin.model.PublicKey
import org.bouncycastle.jce.provider.BouncyCastleProvider
import java.security.Security

fun createBitcoinWalletGenerator() : BitcoinWalletGenerator {
    return BitcoinWalletGenerator(ScryptPBKDF2KeyDerivationFunction(), BitcoinPublicKeyDerivationFunction())
}

// TODO: refactor all this into clean(ish) architecture
class BitcoinWalletGenerator(private val privateKeyDerivationFunction: KeyDerivationFunction,
                             private val publicKeyDerivationFunction: PublicKeyDerivationFunction) {

    init {
        Security.addProvider(BouncyCastleProvider())
    }

    fun generate(passphrase: String, salt: String) : BitcoinWallet {
        val privateKey = deriveKey(passphrase.toByteArray(Charsets.UTF_8), salt.toByteArray(Charsets.UTF_8))
        val publicKey = derivePublicKey(privateKey)

        return BitcoinWallet(privateKey.walletImportFormat, publicKey.address)
    }

    private fun deriveKey(seed: ByteArray, salt: ByteArray): PrivateKey {
        val keyBytes = privateKeyDerivationFunction.deriveKey(seed, salt)

        return PrivateKey(keyBytes)
    }

    private fun derivePublicKey(privateKey: PrivateKey) : PublicKey {
        val keyBytes = publicKeyDerivationFunction.deriveKey(privateKey.bytes)

        return PublicKey(keyBytes)
    }
}

