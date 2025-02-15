package pl.wfranik.moviesapp.ui.common.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import pl.wfranik.moviesapp.R
import pl.wfranik.moviesapp.ui.common.preview.DefaultPreviews
import pl.wfranik.moviesapp.ui.common.theme.MoviesAppTheme
import pl.wfranik.moviesapp.ui.common.theme.dimension

@Composable
fun ErrorSnackbarHost(
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState,
    onDismissed: () -> Unit = {},
) {
    Box(modifier = modifier.fillMaxSize()) {
        SnackbarHost(
            modifier = Modifier.align(Alignment.BottomCenter),
            hostState = snackbarHostState,
        ) {
            val snackbarData = snackbarHostState.currentSnackbarData
            ErrorSnackbarContent(
                message = snackbarData?.messageOrNull(),
                onDismissed = {
                    snackbarData?.dismiss()
                    onDismissed()
                },
            )
        }
    }
}

@Composable
private fun ErrorSnackbarContent(
    modifier: Modifier = Modifier,
    message: String? = null,
    onDismissed: () -> Unit = {},
) {
    Snackbar(
        modifier = modifier
            .padding(
                horizontal = MaterialTheme.dimension.baseMarginLarge,
                vertical = MaterialTheme.dimension.baseMarginMedium,
            ),
        action = {
            TextButton(onClick = { onDismissed() }) {
                Text(
                    text = stringResource(id = R.string.close_label),
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.titleMedium,
                )
            }
        },
        actionOnNewLine = true,
        containerColor = MaterialTheme.colorScheme.errorContainer,
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = message ?: stringResource(id = R.string.error_something_went_wrong),
            style = MaterialTheme.typography.titleMedium,
        )
    }
}

private fun SnackbarData.messageOrNull(): String? {
    return visuals.message.takeIf { it.isNotBlank() }
}

@DefaultPreviews
@Composable
fun PreviewErrorSnackbar() {
    MoviesAppTheme {
        ErrorSnackbarContent(message = "Error message")
    }
}
