package se.olapetersson.julspel

data class Question(val id: Int, val description: String, val correct: Boolean)

data class QuestionDto(val id: Int, val description: String)

object QuestionMapper {
    fun toDto(question: Question): QuestionDto {
        return QuestionDto(question.id, question.description)
    }
}
