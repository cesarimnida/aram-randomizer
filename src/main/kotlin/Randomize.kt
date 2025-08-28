import ChampionProvider.champions
import dev.kord.core.behavior.channel.MessageChannelBehavior
import dev.kord.core.entity.Message
import java.lang.StringBuilder
import kotlin.random.Random

object Randomize {
    suspend fun doIt(messageSplit: List<String>, message: Message) {
        message.channel.createMessage(buildRandomTeam(
            messageSplit.getOrNull(1)?.toIntOrNull() ?: 5,
            messageSplit.getOrNull(2)?.toIntOrNull() ?: 2,
        ))
    }

    private fun buildRandomTeam(
        teamSize: Int,
        champsPerTeamMember: Int
    ): String {
        val team1 = mutableListOf<String>()
        val team2 = mutableListOf<String>()
        for (i in 0 until teamSize) {
            for (j in 0 until champsPerTeamMember) {
                team1.add(getChampFromList())
                team2.add(getChampFromList())
            }
        }
        val completeMessage = StringBuilder()
        completeMessage.appendLine("Personagens roletados")
        completeMessage.appendLine("Time1")
        completeMessage.appendLine(team1.joinToString(separator = "\n"))
        completeMessage.appendLine("")
        completeMessage.appendLine("Time2")
        completeMessage.appendLine(team2.joinToString(separator = "\n"))
        champions.addAll(team1)
        champions.addAll(team2)
        return completeMessage.toString()
    }

    private fun getChampFromList(): String =
        champions.removeAt(Random.nextInt(0, champions.size))
}
