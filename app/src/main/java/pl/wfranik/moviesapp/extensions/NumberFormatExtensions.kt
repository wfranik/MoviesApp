package pl.wfranik.moviesapp.extensions

import android.icu.text.CompactDecimalFormat
import android.icu.util.Currency
import java.util.Locale

fun Long.formatCurrency(): String {
    val formatter = CompactDecimalFormat.getInstance(Locale.US, CompactDecimalFormat.CompactStyle.SHORT)
    formatter.currency = Currency.getInstance("USD")
    formatter.setMaximumFractionDigits(2)
    return formatter.format(this)
}
