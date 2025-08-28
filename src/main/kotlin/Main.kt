import dev.kord.core.Kord
import dev.kord.core.event.message.MessageCreateEvent
import dev.kord.core.on
import dev.kord.gateway.Intent
import dev.kord.gateway.PrivilegedIntent

suspend fun main(args: Array<String>) {
    val client = Kord("CLIENT-ID")
    client.on<MessageCreateEvent> {
        val words = message.content.split("""\s+""".toRegex())
        println("content:${words}")
        when (words[0]) {
            "!aram" -> Randomize.doIt(words, message)
        }
    }
    client.login {
        @OptIn(PrivilegedIntent::class)
        intents += Intent.MessageContent
    }
}


