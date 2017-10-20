package util

import base58.Base58Encoder
import kotlin.experimental.xor

fun ByteArray.toBase58(): String {
    return Base58Encoder().encode(this)
}

fun ByteArray.unshift(value: Byte) : ByteArray {
    return byteArrayOf(value).plus(this)
}

infix fun ByteArray.xor(other: ByteArray) : ByteArray {
    return this.zip(other).map {
        it.first xor it.second
    }.toByteArray()
}
