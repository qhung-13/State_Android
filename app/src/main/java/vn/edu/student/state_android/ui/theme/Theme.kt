package vn.edu.student.state_android.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = Primary,
    primaryContainer = PrimaryContainer,
    background = BackgroundColor,
    surface = SurfaceColor,
    surfaceVariant = SurfaceVariant,
    onBackground = MainText,
    onSurface = MainText,
    error = ErrorColor
)

@Composable
fun StateSurvivalLabTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = AppTypography,
        content = content
    )
}