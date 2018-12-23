package se.olapetersson.julspel

import com.mongodb.client.MongoDatabase
import org.litote.kmongo.eq
import org.litote.kmongo.findOne
import org.litote.kmongo.getCollection
import org.litote.kmongo.replaceOne


class UserRepository(database: MongoDatabase) {
    private val collection = database.getCollection<User>()

    fun insertUser(user: User) {
        collection.insertOne(user)
    }

    fun findUser(alias: String): User? {
        return collection.findOne(User::_id eq alias)
    }

    fun updateUser(user: User) {
        collection.replaceOne(user)
    }

    fun findAll(): List<User> {
        return collection.find().toList()
    }

}
