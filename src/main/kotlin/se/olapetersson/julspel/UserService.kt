package se.olapetersson.julspel

import org.slf4j.LoggerFactory

class UserService(private val userRepository: UserRepository) {

    private val logger = LoggerFactory.getLogger(UserService::javaClass.name)

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
        logger.info("Getting user with id $userId")
        val userBefore = userRepository.findUser(userId)!!
        val userToBeUpdated = userBefore.copy(score = userBefore.score + points)
        logger.info("Updating user with $userToBeUpdated")
        userRepository.updateUser(userToBeUpdated)
    }

    fun createUser(): User {
        var name: String
        do {
            name = availableNames.shuffled().first()
            val foundUser = userRepository.findUser(name)
        } while (foundUser != null)
        val userToCreate = User(name, 0)
        logger.info("Creating new user $userToCreate")
        userRepository.insertUser(userToCreate)
        return userToCreate
    }

    fun getUsers(): List<User> {
        return userRepository.findAll()
    }


}
