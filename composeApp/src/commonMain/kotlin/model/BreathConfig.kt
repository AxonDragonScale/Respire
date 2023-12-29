package model

/**
 * Created by Ronak Harkhani on 29/12/23
 */
sealed class BreathConfig

data class TwoStepBreathConfig(
    val inhale: Long,
    val exhale: Long,
): BreathConfig()

data class ThreeStepBreathConfig(
    val inhale: Long,
    val hold: Long,
    val exhale: Long,
): BreathConfig()

data class FourStepBreathConfig(
    val inhale: Long,
    val inHold: Long,
    val exhale: Long,
    val exHold: Long,
): BreathConfig()
