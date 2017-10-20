package net.rossharper.koin

import net.rossharper.koin.usecases.createBitcoinWalletGenerator

class Koin {
    companion object {
        @JvmStatic fun main(args: Array<String>) {
            // TODO: put some proper CLI around this (this is temp to try it out)
            if (args.size != 2) {
                print("koin <passphrase> <salt>")
                return
            }
            val passphrase = args[0]
            val salt = args[1]
            println("Generating Bitcoin wallet with passphrase '$passphrase' and salt '$salt'...")
            val wallet = createBitcoinWalletGenerator().generate(passphrase, salt)
            println("Private key: ${wallet.privateKey}")
            println("Address: ${wallet.publicKey}")
        }
    }
}
