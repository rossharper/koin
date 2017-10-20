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

private fun parseTestSpec(json: String): TestSpec
        = Moshi.Builder().build().adapter(TestSpec::class.java).fromJson(json)!!

private fun readStringFromResource(classLoader: ClassLoader, resourceName: String): String
        = classLoader.getResourceAsStream(resourceName).bufferedReader().readText()
