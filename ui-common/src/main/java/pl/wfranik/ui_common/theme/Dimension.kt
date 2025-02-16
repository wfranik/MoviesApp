package pl.wfranik.ui_common.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Immutable
data class Dimension(
    val baseMarginHuge: Dp = 52.dp,
    val baseMarginLargest: Dp = 26.dp,
    val baseMarginLarge: Dp = 20.dp,
    val baseMarginMedium: Dp = 12.dp,
    val baseMargin: Dp = 16.dp,
    val baseMarginDouble: Dp = 32.dp,
    val baseMarginSmall: Dp = 10.dp,
    val baseMarginSmallest: Dp = 6.dp,
    val buttonHorizontalMargin: Dp = 20.dp,
    val buttonDefaultHeight: Dp = 48.dp,
    val buttonOutlineWidth: Dp = 2.dp,
    val baseIconSize: Dp = 16.dp,
    val baseButtonPadding: Dp = 12.dp,
    val baseButtonRadius: Dp = 24.dp,
)

@Suppress("ConstructorParameterNaming")
@Immutable
data class Spacing(
    val space64: Dp = 64.dp,
    val space40: Dp = 40.dp,
    val space32: Dp = 32.dp,
    val space30: Dp = 30.dp,
    val space24: Dp = 24.dp,
    val space20: Dp = 20.dp,
    val space18: Dp = 18.dp,
    val space16: Dp = 16.dp,
    val space12: Dp = 12.dp,
    val space10: Dp = 10.dp,
    val space8: Dp = 8.dp,
    val space4: Dp = 4.dp
)

@Immutable
data class FontSize(
    val font34: TextUnit = 34.sp,
    val font32: TextUnit = 32.sp,
    val font28: TextUnit = 28.sp,
    val font26: TextUnit = 26.sp,
    val font24: TextUnit = 24.sp,
    val font22: TextUnit = 22.sp,
    val font18: TextUnit = 18.sp,
    val font16: TextUnit = 16.sp,
    val font14: TextUnit = 14.sp,
    val font12: TextUnit = 12.sp,
    val font10: TextUnit = 10.sp,
    val font8: TextUnit = 8.sp,
)

val LocalSpacing = staticCompositionLocalOf { Spacing() }

val MaterialTheme.spacing: Spacing
    @Composable
    @ReadOnlyComposable
    get() = LocalSpacing.current

val LocalFontSize = staticCompositionLocalOf { FontSize() }

val MaterialTheme.fontSize: FontSize
    @Composable
    @ReadOnlyComposable
    get() = LocalFontSize.current

val LocalDimension = staticCompositionLocalOf { Dimension() }

val MaterialTheme.dimension: Dimension
    @Composable
    @ReadOnlyComposable
    get() = LocalDimension.current
