package se.olapetersson.julspel

class QuestionService(private val questionRepository: QuestionRepository) {

    fun getNextQuestion(): Question {
        return questionRepository.getQuestionById(1)
    }

    fun getQuestion(id: Int): Question {
        return questionRepository.getQuestionById(id)
    }

    fun getQuestions() {
        return questionRepository.getAll()
    }

    fun correctQuestion(answer: Answer) {
        val question = questionRepository.getQuestionById(answer.questionId)
        if(question.correct == answer.answer) {
            // TODO: Update highscore
        }
    }

}
