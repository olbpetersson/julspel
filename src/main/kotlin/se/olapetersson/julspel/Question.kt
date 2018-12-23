package se.olapetersson.julspel

data class Question(val id: Int, val description: String, val correct: Boolean)

data class QuestionDto(val id: Int, val description: String)

object QuestionMapper {
    fun toDto(question: Question): QuestionDto {
        return QuestionDto(question.id, question.description)
    }
}


object Questions {

    fun julRundan(): MutableList<Question> {
        return mutableListOf(
            Question(0, "Den kanadensiska provinsen Nova Scotia är världens största exportör av hummer, vilda blåbär och julgranar.", true),
            Question(1, "I Nordamerika hänger barnen ut sina strumpor medan holländska barn hänger ut sina skor för att få julklappar i.", true),
            Question(2, """En liknande vinterhögtid firades i Rom före kristus under namnet Nativitas men år 44 efter kristus byttes namnet ut till Julafton (Iulii vesper),
                                    ,Namnet kommer av den romerske fältherren och statsmannen Julius Caesar""", false),
            Question(3, "Elektriska julgransljus användes första gången år 1895. Uppfinnaren var amerikanen Ralph E Morris.", true),
            Question(4, "I Sverige och Finland var det förr i tiden dubbla böter på alla brott under julfriden.", true),
            Question(5, "Det uppskattas att ungefär 400 000 människor varje år blir sjuka efter att ha ätit gammal julmat.", true),
            Question(6, "2014 minskade inbrotten i Rinkeby under julhelgen med 27%", true),
            Question(7, "Santa Claus blev under sin livstid bannlyst av vatikanstaten då han tog av kyrkokassan för att ge till behövande barn. Bannlysningen lyftes 11 år efter hans bortgång.", false)

        )
    }

    fun testRundan(): MutableList<Question> {
        return mutableListOf(
            Question(0, "ACCORDING TO AMAZON, THE MOST HIGHLIGHTED KINDLE BOOKS ARE THE BIBLE, THE STEVE JOBS BIOGRAPHY, AND THE HUNGER GAMES.", true),
            Question(1, "IN THE MID-1960S, SLUMBER PARTY BARBIE CAME WITH A BOOK CALLED 'HOW TO LOSE WEIGHT.' ONE OF THE TIPS WAS 'DON’T EAT'.", false),
            Question(2, "IN A 2008 SURVEY, 58% OF BRITISH TEENS THOUGHT SHERLOCK HOLMES WAS A REAL GUY, WHILE 20% THOUGHT WINSTON CHURCHILL WAS NOT.", true),
            Question(3, "A WOMAN FROM TEXAS ONCE SUED MCDONALDS FOR FALSE MARKETING. NONE OF THEIR BURGERS CONTAINED MACARONI", false)
            )
    }
}
