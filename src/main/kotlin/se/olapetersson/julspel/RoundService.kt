package se.olapetersson.julspel

import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory
import kotlin.concurrent.fixedRateTimer

class RoundService(private val roundRepository: RoundRepository,
                   private val userService: UserService,
                   private val oneSignalClient: OneSignalClient,
                   private val roundTime: Long) {

    val logger = LoggerFactory.getLogger(RoundService::javaClass.name)


    fun givePointsToUser(userId: String) {
        val currentRound = getCurrentRound()
        val hasntAnsweredBefore = currentRound!!.respondees.none { respondee ->
            respondee == userId
        }
        if (hasntAnsweredBefore) {
            userService.givePointsToUser(currentRound.pointsLeft, userId)
            logger.info("Giving ${currentRound.pointsLeft} to $userId")
            if (currentRound.pointsLeft > 0) {
                roundRepository.updateRound(
                    currentRound.copy(
                        pointsLeft = currentRound.pointsLeft - 2,
                        respondees = currentRound.respondees + userId
                    )
                )
            }
        } else {
            logger.info("$userId tried to cheat buhu!")
        }
    }

    fun startRounds() {
        try {
            fixedRateTimer(name = "The game engine", period = roundTime) {
                runBlocking {

                    logger.info("ding ding ding!")
                    nextRound()
                    oneSignalClient.sendNotification()

                }
            }
        } catch (e: Exception) {
            logger.error("Schedule crashed, probably out of questions", e)
        }
    }

    fun getCurrentRound(): Round? {
        return roundRepository.getCurrent()
    }

    fun nextRound() {
        val currentRound = getCurrentRound()
        var nextRound = Round(GAME_ID, 0, MAX_POINTS, emptyList())
        if (currentRound != null) {
            nextRound = currentRound.copy(currentRoundIndex = currentRound.currentRoundIndex + 1)
            roundRepository.updateRound(nextRound)
        } else {
            roundRepository.create(nextRound)
        }
        logger.info("Bumping next round $nextRound")

    }

    fun whipe() {
        roundRepository.removeGame()
    }

    fun markUserForCurrentRound(userId: String) {
        val currentRound = getCurrentRound()
        roundRepository.updateRound(currentRound!!.copy(respondees = currentRound.respondees + userId))
    }

}
