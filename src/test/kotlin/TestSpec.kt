import com.squareup.moshi.Moshi
import java.io.BufferedReader
import java.io.InputStreamReader

data class TestSpec(val vectors: List<TestVector>)
data class TestVector(val passphrase: String, val salt: String, val seeds: List<String>, val keys: Keys)
data class Keys(val private: String, val public: String)

fun loadTestSpec(): TestSpec
        = parseTestSpec(readStringFromResource(
        BitcoinWalletGeneratorShould::class.java.classLoader,
        "warpwallettestspec.json"))

private fun parseTestSpec(json: String): TestSpec {
    return Moshi.Builder().build().adapter(TestSpec::class.java).fromJson(json)!!
}

private fun readStringFromResource(classLoader: ClassLoader, resourceName: String): String {

    val inputStream = classLoader.getResourceAsStream(resourceName)
    val streamReader = InputStreamReader(inputStream)
    val stringBuilder = StringBuilder()
    val bufferedReader = BufferedReader(streamReader)

    var read: String? = bufferedReader.readLine()

    while (read != null) {
        stringBuilder.append(read)
        read = bufferedReader.readLine()
    }

    return stringBuilder.toString()
}
