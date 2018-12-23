package se.olapetersson.julspel

import com.mongodb.client.MongoDatabase
import org.litote.kmongo.eq
import org.litote.kmongo.findOne
import org.litote.kmongo.getCollection
import org.litote.kmongo.updateOne


class UserRepository(private val database: MongoDatabase) {
    val collection = database.getCollection<User>()

    fun insertUser(user: User) {
        collection.insertOne(user)
    }

    fun findUser(alias: String): User? {
        return collection.findOne(User::alias eq alias)
    }

    fun updateUser(user: User) {
        collection.updateOne(User::alias eq user.alias)
    }

}
