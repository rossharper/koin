package base58

class Base58Encoder {
    private val alphabet = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz".toCharArray()
    private val zero = alphabet[0]

    fun encode(data: ByteArray): String {
        if (data.isEmpty()) {
            return ""
        }
        val leadingZeroCount = data.indexOfFirst { it != 0.toByte() }
        val input = data.copyOf()
        var output = CharArray(data.size * 2).apply { fill(zero) }
        var inputIndex = leadingZeroCount
        var outputIndex = 0
        while(inputIndex < input.size) {
            output[outputIndex] = alphabet[divmod(input, inputIndex).toInt()]
            if(input[inputIndex].toInt() == 0) {
                inputIndex++
            }
            outputIndex++
        }
        output = output.dropLastWhile { it == zero }.toCharArray()
        output = output.plus(CharArray(leadingZeroCount).apply{fill(zero)})
        return String(output.reversed().toCharArray())
    }

    private fun divmod(input: ByteArray, startIndex: Int): Byte {
        var remainder = 0
        val divisor = 58
        val base = 256
        for (i in startIndex..input.size - 1) {
            val digit = input[i].toInt() and 0xFF
            val temp = remainder * base + digit
            input[i] = (temp / divisor).toByte()
            remainder = temp % divisor
        }
        return remainder.toByte()
    }
}