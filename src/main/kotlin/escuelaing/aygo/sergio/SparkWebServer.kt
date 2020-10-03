package escuelaing.aygo.sergio

import escuelaing.aygo.sergio.repositories.WordsRepository
import escuelaing.aygo.sergio.static.HOME_PAGE
import spark.Request
import spark.kotlin.get
import spark.kotlin.port

class SparkWebServer(private val wordsRepository: WordsRepository) {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val instance = SparkWebServer(WordsRepository())

            port(getPort())
            get("/") { HOME_PAGE }
            get("/save") { instance.saveInAPI(this.request) }
            get("/words") { instance.getWordsFromAPI() }
        }

        private fun getPort(): Int = if (System.getenv("PORT") != null) {
            System.getenv("PORT").toInt()
        } else 4567
    }

    private fun getWordsFromAPI(): String = wordsRepository.find(10).fold("") { acc, word ->
        "$acc<br>$word"
    }.ifBlank { "No records found" }

    private fun saveInAPI(req: Request): String {
        val word = req.queryParams("word")
        return wordsRepository.save(word)
    }
}

