import java.io.File
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

fun getInput(day: Int): List<String> {
    val path = "src/inputs/Day$day.txt"
    val url = "https://adventofcode.com/2021/day/$day/input"

    val file = File(path)

    if (!file.exists()) {
        file.createNewFile()

        val client = HttpClient.newBuilder().build()
        val request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("Cookie", "session=" + File("sessionKey.txt").readText())
            .build()

        val response = client.send(request, HttpResponse.BodyHandlers.ofString())
        file.writeText(response.body())
    }

    return file.readLines()
}