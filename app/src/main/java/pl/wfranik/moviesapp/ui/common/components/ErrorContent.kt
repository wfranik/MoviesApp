package pl.wfranik.moviesapp.ui.common.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import pl.wfranik.moviesapp.R
import pl.wfranik.moviesapp.ui.common.preview.DefaultPreviews
import pl.wfranik.moviesapp.ui.common.theme.MoviesAppTheme
import pl.wfranik.moviesapp.ui.common.utils.TextLabel

@Composable
fun ErrorContent(
    modifier: Modifier = Modifier,
    onRetry: () -> Unit = {},
) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = stringResource(R.string.error_loading_data_failed),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.error
        )
        Spacer(modifier = Modifier.padding(12.dp))
        PrimaryButton(
            text = TextLabel(R.string.retry_label),
            onClick = onRetry,
        )
    }
}

@DefaultPreviews
@Composable
private fun PreviewErrorContent() {
    MoviesAppTheme {
        ErrorContent(
            modifier = Modifier,
        )
    }
}
