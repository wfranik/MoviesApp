package pl.wfranik.moviesapp.ui.common.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import pl.wfranik.moviesapp.R
import pl.wfranik.moviesapp.ui.common.preview.DefaultPreviews
import pl.wfranik.moviesapp.ui.common.theme.MoviesAppTheme

@Composable
fun EmptyListContent(
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = stringResource(R.string.error_no_data),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@DefaultPreviews
@Composable
private fun PreviewEmptyListContent() {
    MoviesAppTheme {
        EmptyListContent(
            modifier = Modifier,
        )
    }
}
