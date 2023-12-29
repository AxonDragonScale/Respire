package ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.delay
import model.BreathState.EXHALE
import model.BreathState.INHALE
import model.TwoStepBreathConfig

/**
 * Created by Ronak Harkhani on 29/12/23
 */

@Composable
fun CircleBreath(
    modifier: Modifier = Modifier,
    config: TwoStepBreathConfig,
) {
    Box(
        modifier = modifier
            .aspectRatio(1F)
            .fillMaxSize(0.8F)
            .background(Color.Transparent),
        contentAlignment = Alignment.Center,
    ) {
        // Outer Circle
        Box(
            modifier = Modifier
                .fillMaxSize(0.8F)
                .clip(CircleShape)
                .background(Color.Red.copy(alpha = 0.5F))
        )

        // Animating Circle
        var state by remember { mutableStateOf(EXHALE) }
        val animatableSize = remember { Animatable(0.4F) }
        LaunchedEffect(Unit) {
            while (true) {
                state = if (state == INHALE) EXHALE else INHALE
                delay(if (state == INHALE) config.inhale else config.exhale)
            }
        }
        LaunchedEffect(state) {
            animatableSize.animateTo(
                targetValue = if (state == INHALE) 0.8F else 0.4F,
                animationSpec = tween(
                    durationMillis = if (state == INHALE) config.inhale.toInt()
                                     else config.exhale.toInt(),
                    easing = LinearEasing
                )
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize(animatableSize.value)
                .clip(CircleShape)
                .background(Color.Red.copy(alpha = 0.7F))
        )

        // Inner Circle
        Box(
            modifier = Modifier
                .fillMaxSize(0.4F)
                .clip(CircleShape)
                .background(Color.Red)
        )
    }
}
