package se.olapetersson.julspel

import com.mongodb.client.MongoDatabase
import org.litote.kmongo.KMongo


class MongoDriver {

    fun setup(mongoUrl: String): MongoDatabase {
        val client = KMongo.createClient(mongoUrl) //get com.mongodb.MongoClient new instance
        val database = client.getDatabase("test") //normal java driver usage
        return database
    }
}
