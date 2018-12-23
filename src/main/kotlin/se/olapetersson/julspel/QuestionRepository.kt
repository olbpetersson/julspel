package se.olapetersson.julspel

import com.mongodb.client.MongoDatabase
import org.litote.kmongo.eq
import org.litote.kmongo.findOne
import org.litote.kmongo.getCollection

class QuestionRepository(private val database: MongoDatabase) {
    val collection = database.getCollection<Question>()

    var questions = Questions.julRundan()

    init {
        if (collection.countDocuments() <= 0) {
            println("inserting")
            collection.insertMany(questions)
        }
    }

    fun getQuestionById(id: Int): Question {
        return collection.findOne(Question::id eq id)!!
    }

    fun getAll(): List<Question> {
        return collection.find().toList()
    }

}
