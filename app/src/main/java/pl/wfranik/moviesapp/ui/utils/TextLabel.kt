package pl.wfranik.moviesapp.ui.utils

import android.content.Context
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import androidx.compose.ui.platform.LocalContext

@Stable
sealed interface TextLabel {

    operator fun invoke(context: Context): String = when (this) {
        is ResourceTextLabel -> resolveText(context)
        is StringTextLabel -> resolveText()
        is ConcatenatedTextLabel -> resolveText(context)
        is PluralTextLabel -> resolveText(context)
    }

    operator fun plus(textLabel: TextLabel?) = ConcatenatedTextLabel(*listOfNotNull(this, textLabel).toTypedArray())

    class ConcatenatedTextLabel(vararg val textLabels: TextLabel) : TextLabel {

        fun resolveText(context: Context): String =
            textLabels.asList().joinToString(separator = "") { it(context) }

        override fun equals(other: Any?) =
            other is ConcatenatedTextLabel && other.textLabels.asList() == textLabels.asList()

        override fun hashCode() = textLabels.hashCode()
    }

    class ResourceTextLabel(@StringRes val textId: Int, private vararg val params: Any) : TextLabel {

        fun resolveText(context: Context): String {
            return context.getString(textId, *params)
        }

        override fun equals(other: Any?) =
            other is ResourceTextLabel && other.textId == textId && other.params contentEquals params

        override fun hashCode() = textId.hashCode()
    }

    class StringTextLabel(private val text: String) : TextLabel {

        fun resolveText() = text

        override fun equals(other: Any?) = other is StringTextLabel && other.text == text

        override fun hashCode() = text.hashCode()
    }

    data class PluralTextLabel(private val pluralStringParams: PluralStringParams) : TextLabel {

        fun resolveText(context: Context): String {
            return pluralStringParams.let {
                context.resources.getQuantityString(
                    it.pluralResId,
                    it.pluralQuantity,
                    it.pluralQuantity
                )
            }
        }

        data class PluralStringParams(@PluralsRes val pluralResId: Int, val pluralQuantity: Int)
    }

    companion object {
        val empty = TextLabel("")

        operator fun invoke(@StringRes textId: Int, vararg params: Any) =
            ResourceTextLabel(textId, *params)

        operator fun invoke(text: String) = StringTextLabel(text)

        operator fun invoke(pluralStringParams: PluralTextLabel.PluralStringParams) =
            PluralTextLabel(pluralStringParams)
    }
}

fun String?.toLabelOrNull(): TextLabel? = this?.let { TextLabel(this) }
fun String?.toLabelIfNotBlank(): TextLabel? = this?.takeIf { it.isNotBlank() }.toLabelOrNull()
fun String?.toLabelOrEmpty(): TextLabel = toLabelOrNull() ?: TextLabel.empty

@ReadOnlyComposable
@Composable
fun TextLabel.getText(): String = invoke(LocalContext.current)
