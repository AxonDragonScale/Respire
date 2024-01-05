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
import model.BreathState
import model.Exhale
import model.Inhale
import model.TwoStepBreathConfig

/**
 * Created by Ronak Harkhani on 29/12/23
 */

@Composable
fun CircleBreath(
    modifier: Modifier = Modifier,
    config: TwoStepBreathConfig,
) {
    Box(modifier = modifier) {
        Box(
            // Aspect Ratio does not work properly if there is another modifier like fillMaxSize
            // before it. So, keeping it in a separate.
            modifier = Modifier.aspectRatio(1F),
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
            var state by remember { mutableStateOf<BreathState>(Exhale(0L)) }
            val animatableSize = remember { Animatable(0.4F) }
            LaunchedEffect(Unit) {
                while (true) {
                    state = if (state is Inhale) Exhale(config.exhale) else Inhale(config.inhale)
                    delay(state.duration)
                }
            }
            LaunchedEffect(state) {
                animatableSize.animateTo(
                    targetValue = if (state is Inhale) 0.8F else 0.4F,
                    animationSpec = tween(
                        durationMillis = state.duration.toInt(),
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
}
