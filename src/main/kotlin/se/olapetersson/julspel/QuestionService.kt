package se.olapetersson.julspel

class QuestionService(private val questionRepository: QuestionRepository) {

    fun getOngoingQuestion(roundIndex: Int): Question {
        return questionRepository.getQuestionById(roundIndex)
    }

    fun getQuestions(): List<Question> {
        return questionRepository.getAll()
    }

    fun validateAnswer(answer: Answer): Boolean {
        val question = questionRepository.getQuestionById(answer.questionId)
        return question.correct == answer.answer
    }

}
