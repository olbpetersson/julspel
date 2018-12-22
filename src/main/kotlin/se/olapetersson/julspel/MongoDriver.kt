package se.olapetersson.julspel

import com.mongodb.client.MongoDatabase
import org.litote.kmongo.KMongo


class MongoDriver {

    fun setup(): MongoDatabase {
        val client = KMongo.createClient() //get com.mongodb.MongoClient new instance
        val database = client.getDatabase("test") //normal java driver usage
        return database
        //database.getCollection()
        //val col = database.<Jedi>() //KMongo extension method
//here the name of the collection by convention is "jedi"
//you can use getCollection<Jedi>("otherjedi") if the collection name is different

        // col.insertOne(Jedi("Luke Skywalker", 19))

        // val yoda: Jedi? = col.findOne(Jedi::name eq "Yoda")
    }
}
