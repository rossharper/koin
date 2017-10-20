package net.rossharper.koin.model

import util.toBase58
import util.unshift
import java.security.MessageDigest

data class PublicKey(val bytes: ByteArray) {
    val address : String
        get() {
            // Convert to bitcoin Address...
            // Add network version
            val addressHash = bytes.unshift(0x00) // network version, mainnet
            // Checksum (double sha256)
            val pubKeyChecksum = MessageDigest.getInstance("SHA-256").digest(MessageDigest.getInstance("SHA-256").digest(addressHash))
            // Address = checksummed address hash
            val address = addressHash.plus(pubKeyChecksum.copyOfRange(0, 4))
            return address.toBase58()
        }
}