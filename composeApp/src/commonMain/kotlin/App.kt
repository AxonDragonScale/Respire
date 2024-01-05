import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import model.FourStepBreathConfig
import model.TwoStepBreathConfig
import ui.BoxBreath
import ui.CircleBreath

@Composable
fun App() {
    MaterialTheme {
//        CircleBreath(
//            modifier = Modifier.fillMaxSize(),
//            config = TwoStepBreathConfig(inhale = 1000, exhale = 4000)
//        )

        BoxBreath(
            modifier = Modifier.fillMaxSize(),
            config = FourStepBreathConfig(
                inhale = 2000,
                inHold = 2000,
                exhale = 2000,
                exHold = 2000
            )
        )
    }
}
