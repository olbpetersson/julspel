package se.olapetersson.julspel

import com.mongodb.client.MongoDatabase
import org.litote.kmongo.eq
import org.litote.kmongo.findOne
import org.litote.kmongo.getCollection
import org.litote.kmongo.updateOne

const val GAME_ID = 1
const val MAX_POINTS = 10

class RoundRepository(private val database: MongoDatabase) {
    val collection = database.getCollection<Round>()

    fun create(round: Round) {
        collection.insertOne(round)
    }

    fun updateRound(round: Round) {

        collection.updateOne(Round::gameId eq GAME_ID, round)
    }

    fun getCurrent(): Round? {
        return collection.findOne(Round::gameId eq GAME_ID)
    }

    fun removeGame() {
        collection.deleteOne(Round::gameId eq GAME_ID)
    }

}
