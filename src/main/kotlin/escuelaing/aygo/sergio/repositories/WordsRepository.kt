package escuelaing.aygo.sergio.repositories

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Sorts.descending
import escuelaing.aygo.sergio.models.Record
import org.bson.codecs.configuration.CodecRegistries.fromProviders
import org.bson.codecs.configuration.CodecRegistries.fromRegistries
import org.bson.codecs.pojo.PojoCodecProvider
import java.util.*

class WordsRepository {
    private val collection: MongoCollection<Record>

    init {
        val host = System.getenv("MONGO_HOST") ?: "localhost"
        val pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build())
        val settings = MongoClientSettings.builder()
            .codecRegistry(fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry))
            .applyConnectionString(ConnectionString("mongodb://$host:27017"))
            .build()
        val mongoClient = MongoClients.create(settings)
        val db = mongoClient.getDatabase("spark-app")
        collection = db.getCollection("words", Record::class.java)
    }


    fun find(n: Int): List<Record> {
        return collection.find().sort(descending("date")).limit(n).toList()
    }

    fun save(word: String): String {
        return try {
            collection.insertOne(Record(word, Date()))
            "Ok"
        } catch (e: Throwable) {
            e.message.toString()
        }
    }
}