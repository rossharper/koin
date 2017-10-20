import org.bouncycastle.util.encoders.Hex

fun String.toHexDecodedByteArray(): ByteArray = Hex.decode(this)