package util

import kotlin.experimental.xor

val ByteArray.base58: String
    get() {
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
