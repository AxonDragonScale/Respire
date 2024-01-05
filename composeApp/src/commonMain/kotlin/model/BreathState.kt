package model


/**
 * Created by Ronak Harkhani on 29/12/23
 */
sealed class BreathState(open val duration: Long)

data class Inhale(override val duration: Long): BreathState(duration)
data class Inhold(override val duration: Long): BreathState(duration)
data class Exhale(override val duration: Long): BreathState(duration)
data class Exhold(override val duration: Long): BreathState(duration)
