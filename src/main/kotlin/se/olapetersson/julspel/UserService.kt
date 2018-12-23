package se.olapetersson.julspel

class UserService(private val userRepository: UserRepository) {
    val availableNames = listOf(
        "ADVENT",
        "LUCIA",
        "JULGRAN",
        "MISTEL",
        "LJUSSTAKE",
        "JULAFTON",
        "REN",
        "PAKET",
        "JULDAGEN",
        "JULMARKNAD",
        "JULAFTON",
        "RIM",
        "JULTOMTE")

    fun givePointsToUser(points: Int, userId: String) {
        val userBefore = userRepository.findUser(userId)!!
        userRepository.updateUser(userBefore.copy(score = userBefore.score + points))
    }

    fun createUser(): User {
        var name: String
        do {
            name = availableNames.shuffled().first()
            val foundUser = userRepository.findUser(name)
        } while (foundUser != null)
        val userToCreate = User(name, 0)
        userRepository.insertUser(userToCreate)
        return userToCreate
    }


}
