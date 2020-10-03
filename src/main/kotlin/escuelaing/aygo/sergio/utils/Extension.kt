package escuelaing.aygo.sergio.utils

import com.mongodb.client.FindIterable
import com.mongodb.client.MongoCollection
import org.bson.Document
import org.bson.conversions.Bson

@Suppress("EXTENSION_SHADOWED_BY_MEMBER")
inline fun <reified T> MongoCollection<Document>.find(): FindIterable<T> = find(T::class.java)