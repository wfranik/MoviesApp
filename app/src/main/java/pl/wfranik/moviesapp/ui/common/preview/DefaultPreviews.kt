package pl.wfranik.moviesapp.ui.common.preview

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

const val COMPOSE_DARK_THEME_PREVIEW_BACKGROUND_COLOR = 0xFF262628

@Preview(name = "Light theme", showBackground = true)
@Preview(
    name = "Dark theme",
    showBackground = true,
    backgroundColor = COMPOSE_DARK_THEME_PREVIEW_BACKGROUND_COLOR,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
annotation class DefaultPreviews
