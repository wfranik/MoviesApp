package pl.wfranik.moviesapp.ui.common.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import pl.wfranik.moviesapp.ui.common.preview.DefaultPreviews
import pl.wfranik.moviesapp.ui.common.theme.MoviesAppTheme
import pl.wfranik.moviesapp.ui.common.theme.dimension
import pl.wfranik.moviesapp.ui.common.utils.TextLabel
import pl.wfranik.moviesapp.ui.common.utils.getText

@Composable
fun primaryButtonColors(): ButtonColors = ButtonDefaults.buttonColors(
    containerColor = MaterialTheme.colorScheme.primary,
    contentColor = MaterialTheme.colorScheme.onPrimary,
//    disabledContainerColor = MaterialTheme.colorScheme.grey,
//    disabledContentColor = MaterialTheme.color.textPrimaryColor
)

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: TextLabel,
    textAlign: TextAlign? = TextAlign.Center,
    enabled: Boolean = true,
    buttonColors: ButtonColors = primaryButtonColors()
) {
    PrimaryButton(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        buttonColors = buttonColors
    ) {
        Text(
            text = text.getText(),
            style = MaterialTheme.typography.titleMedium,
            textAlign = textAlign
        )
    }
}

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean = true,
    buttonColors: ButtonColors = primaryButtonColors(),
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        elevation = null,
        shape = RoundedCornerShape(size = MaterialTheme.dimension.baseButtonRadius),
        colors = buttonColors,
        enabled = enabled,
        onClick = onClick,
        contentPadding = contentPadding,
        modifier = modifier.defaultMinSize(minHeight = MaterialTheme.dimension.buttonDefaultHeight)
    ) {
        content()
    }
}

@Composable
fun borderButtonColors(): ButtonColors = ButtonDefaults.outlinedButtonColors(
//    containerColor = MaterialTheme.colorScheme.primary,
//    contentColor = MaterialTheme.colorScheme.primary,
//    disabledContentColor = MaterialTheme.colorScheme.onSurface
)

@Composable
fun OutlinedButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: TextLabel,
    textAlign: TextAlign? = TextAlign.Center,
    enabled: Boolean = true,
    buttonColors: ButtonColors = borderButtonColors()
) {
    OutlinedButton(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        buttonColors = buttonColors
    ) {
        Text(
            text = text.getText(),
            style = MaterialTheme.typography.titleMedium,
            textAlign = textAlign
        )
    }
}

@Composable
fun OutlinedButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean = true,
    buttonColors: ButtonColors = borderButtonColors(),
    content: @Composable RowScope.() -> Unit
) {
    OutlinedButton(
        modifier = modifier.defaultMinSize(minHeight = MaterialTheme.dimension.buttonDefaultHeight),
        onClick = onClick,
        enabled = enabled,
        border = BorderStroke(
            MaterialTheme.dimension.buttonOutlineWidth,
            if (enabled) MaterialTheme.colorScheme.primary else Color.Gray
        ),
        shape = RoundedCornerShape(size = MaterialTheme.dimension.baseButtonRadius)
    ) {
        content()
    }
}

@DefaultPreviews
@Composable
fun PreviewPrimaryButton() {
    MoviesAppTheme {
        PrimaryButton(
            text = TextLabel("Click me"),
            onClick = {}
        )
    }
}

@DefaultPreviews
@Composable
fun PreviewPrimaryDisabledButton() {
    MoviesAppTheme {
        PrimaryButton(
            text = TextLabel("Click me"),
            onClick = {},
            enabled = false
        )
    }
}

@DefaultPreviews
@Composable
fun PreviewOutlinedButton() {
    MoviesAppTheme {
        OutlinedButton(
            text = TextLabel("Click me"),
            onClick = {}
        )
    }
}

@DefaultPreviews
@Composable
fun PreviewOutlinedDisabledButton() {
    MoviesAppTheme {
        OutlinedButton(
            text = TextLabel("Click me"),
            onClick = {},
            enabled = false
        )
    }
}
