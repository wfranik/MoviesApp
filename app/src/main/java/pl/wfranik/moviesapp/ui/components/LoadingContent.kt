package pl.wfranik.moviesapp.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import pl.wfranik.moviesapp.ui.preview.DefaultPreviews
import pl.wfranik.moviesapp.ui.theme.MoviesAppTheme

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
