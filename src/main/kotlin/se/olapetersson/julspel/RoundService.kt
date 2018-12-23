package se.olapetersson.julspel

import org.slf4j.LoggerFactory
import kotlin.concurrent.fixedRateTimer

class RoundService(private val roundRepository: RoundRepository,
                   private val userService: UserService) {

    val logger = LoggerFactory.getLogger(RoundService::javaClass.name)


    fun givePointsToUser(userId: String) {
        val currentRound = getCurrentRound()
        userService.givePointsToUser(currentRound!!.pointsLeft, userId)
        if (currentRound.pointsLeft > 0) {
            roundRepository.updateRound(currentRound.copy(pointsLeft = currentRound.pointsLeft - 2))
        }
    }

    fun startRounds() {
        fixedRateTimer(name = "The game engine", period = 60_000) {
            try {
                logger.info("ding ding ding!")
                nextRound()
            } catch (e: Exception) {
                logger.error("Schedule crashed, probably out of questions", e)
            }
        }
    }

    fun getCurrentRound(): Round? {
        return roundRepository.getCurrent()
    }

    fun nextRound() {
        val currentRound = getCurrentRound()
        var nextRound = Round(GAME_ID, 0, MAX_POINTS)
        if(currentRound != null) {
            nextRound = currentRound.copy(currentRoundIndex = currentRound.currentRoundIndex + 1)
            roundRepository.updateRound(nextRound)
        } else {
            roundRepository.create(nextRound)
        }
        logger.info("Bumping next round ${nextRound.currentRoundIndex}")

    }

}
