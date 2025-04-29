package com.lapoushko.extension

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

/**
 * @author Lapoushko
 */
fun String.toDate(): LocalDate{
    val localDate = LocalDate.parse(this)
    return localDate
}

fun LocalDate.toCustomString(): String {
    val outputFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale("ru"))
    val formattedDate: String = this.format(outputFormatter)

    return formattedDate.split(" ")
        .joinToString(" ") {
            if (it.length > 1) it.replaceFirstChar { char -> char.uppercase() } else it
        }
}