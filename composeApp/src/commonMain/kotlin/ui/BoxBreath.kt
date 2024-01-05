package ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntSize
import kotlinx.coroutines.delay
import model.BreathState
import model.Exhale
import model.Exhold
import model.FourStepBreathConfig
import model.Inhale
import model.Inhold

/**
 * Created by Ronak Harkhani on 29/12/23
 */

@Composable
fun BoxBreath(
    modifier: Modifier = Modifier,
    config: FourStepBreathConfig,
) {
    Box(modifier = modifier) {

        var size by remember { mutableStateOf(IntSize.Zero) }
        Box(
            modifier = Modifier
                .aspectRatio(1F)
                .onSizeChanged { size = it },
            contentAlignment = Alignment.Center
        ) {
            var state by remember { mutableStateOf<BreathState>(Exhold(0L)) }
            LaunchedEffect(Unit) {
                while (true) {
                    state = when (state) {
                        is Exhold -> Inhale(config.inhale)
                        is Inhale -> Inhold(config.inHold)
                        is Inhold -> Exhale(config.exhale)
                        is Exhale -> Exhold(config.exHold)
                    }
                    delay(state.duration)
                }
            }
            val x = remember { Animatable(0F) }
            val y = remember { Animatable(1F, 0.1F) }
            LaunchedEffect(state) {
                if (state is Inhale || state is Exhale) {
                    y.animateTo(
                        targetValue = if (state is Inhale) 0F else 1F,
                        animationSpec = tween(
                            durationMillis = state.duration.toInt(),
                            easing = LinearEasing
                        )
                    )
                }
                if (state is Inhold || state is Exhold) {
                    x.animateTo(
                        targetValue = if (state is Inhold) 1F else 0F,
                        animationSpec = tween(
                            durationMillis = state.duration.toInt(),
                            easing = LinearEasing
                        )
                    )
                }
            }

            val rectSize = Size(size.width * 0.8F, size.height * 0.8F)
            Canvas(modifier = Modifier.fillMaxSize(0.8F)) {
                drawRect(
                    color = Color.Blue.copy(alpha = 0.5F),
                    size = rectSize
                )
                drawRect(
                    color = Color.Blue.copy(alpha = (1 - y.value)),
                    topLeft = Offset(0F, rectSize.height * y.value),
                    size = Size(rectSize.width, rectSize.height * (1 - y.value))
                )
                drawCircle(
                    color = Color.Blue,
                    radius = 20F,
                    center = Offset(rectSize.width * x.value, rectSize.height * y.value)
                )
            }
        }
    }
}
