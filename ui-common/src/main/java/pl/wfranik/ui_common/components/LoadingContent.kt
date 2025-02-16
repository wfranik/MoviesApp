package pl.wfranik.ui_common.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import pl.wfranik.ui_common.preview.DefaultPreviews
import pl.wfranik.ui_common.theme.MoviesAppTheme

@Composable
fun LoadingContent(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@DefaultPreviews
@Composable
private fun PreviewLoadingContent() {
    MoviesAppTheme {
        LoadingContent(
            modifier = Modifier,
        )
    }
}
