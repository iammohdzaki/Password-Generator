package utils

import java.security.MessageDigest
import java.util.*
import kotlin.text.Charsets.UTF_8

object Generators {

    fun generateUniqueKey(length: Int = 8, vararg keys: String): String {
        // Get the current time in nanoseconds since some fixed but arbitrary origin
        val nanoTime = System.nanoTime()

        // Combine the input key and nanosecond time
        val combined = "${keys.joinToString(",")}$nanoTime"

        // Get a cryptographic hash (SHA-256)
        val digest = MessageDigest.getInstance("SHA-256")
        val hash = digest.digest(combined.toByteArray(UTF_8))

        // Encode the hash to Base64 to make it URL-safe and trim to the required length
        val base64Encoded = Base64.getUrlEncoder().withoutPadding().encodeToString(hash)

        // Return the unique key of the specified length
        return base64Encoded.substring(0, length)
    }
}