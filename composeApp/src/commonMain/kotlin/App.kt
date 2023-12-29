import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import model.TwoStepBreathConfig
import ui.CircleBreath

@Composable
fun App() {
    MaterialTheme {
        CircleBreath(config = TwoStepBreathConfig(1000, 4000))
    }
}
