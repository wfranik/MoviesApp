package pl.wfranik.moviesapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import pl.wfranik.moviesapp.R
import pl.wfranik.moviesapp.ui.preview.DefaultPreviews
import pl.wfranik.moviesapp.ui.theme.MoviesAppTheme

@Composable
fun PlaceholderComponent(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Icon(
            modifier = Modifier
                .align(Alignment.Center)
                .size(48.dp),
            painter = painterResource(R.drawable.outline_image_24),
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            contentDescription = stringResource(R.string.image_placeholder_description)
        )
    }
}

@DefaultPreviews
@Composable
private fun PlaceholderComponentPreview() {
    MoviesAppTheme {
        PlaceholderComponent()
    }
}
