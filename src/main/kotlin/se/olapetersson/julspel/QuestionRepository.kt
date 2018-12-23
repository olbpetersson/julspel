package se.olapetersson.julspel

import com.mongodb.client.MongoDatabase
import org.litote.kmongo.eq
import org.litote.kmongo.findOne
import org.litote.kmongo.getCollection

class QuestionRepository(private val database: MongoDatabase) {
    val collection = database.getCollection<Question>()

    var questions = mutableListOf<Question>(
        Question(1, "Den kanadensiska provinsen Nova Scotia är världens största exportör av hummer, vilda blåbär och julgranar.", true),
        Question(2, "I Nordamerika hänger barnen ut sina strumpor medan holländska barn hänger ut sina skor för att få julklappar i.", true),
        Question(3, """En liknande vinterhögtid firades i Rom före kristus under namnet Nativitas men år 44 efter kristus byttes namnet ut till Julafton (Iulii vesper),
                                    ,Namnet kommer av den romerske fältherren och statsmannen Julius Caesar""", false),
        Question(4, "Elektriska julgransljus användes första gången år 1895. Uppfinnaren var amerikanen Ralph E Morris.", true),
        Question(5, "I Sverige och Finland var det förr i tiden dubbla böter på alla brott under julfriden.", true),
        Question(6, "Det uppskattas att ungefär 400 000 människor varje år blir sjuka efter att ha ätit gammal julmat.", true),
        Question(7, "2014 minskade inbrotten i Rinkeby under julhelgen med 27%", true),
        Question(8, "Santa Claus blev under sin livstid bannlyst av vatikanstaten då han tog av kyrkokassan för att ge till behövande barn. Bannlysningen lyftes 11 år efter hans bortgång.", false)

    )

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
