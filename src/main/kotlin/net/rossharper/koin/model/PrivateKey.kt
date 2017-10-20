package net.rossharper.koin.model

import util.base58
import util.unshift
import java.security.MessageDigest

data class PrivateKey(val bytes : ByteArray) {
    val walletImportFormat : String
        get() {
            val eckeybytes = bytes.unshift(0x80.toByte())
            val shaDigest = MessageDigest.getInstance("SHA-256")
            val checksum = shaDigest.digest(shaDigest.digest(eckeybytes))
            val checksummedKey = eckeybytes.plus(checksum.copyOfRange(0, 4))
            return checksummedKey.base58
        }
}