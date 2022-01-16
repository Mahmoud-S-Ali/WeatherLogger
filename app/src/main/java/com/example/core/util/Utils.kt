package com.example.core.util

import timber.log.Timber
import java.lang.Exception
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class Utils {
    companion object {
        fun formatDate(date: String?): String {
            var dateStr = ""

            val dateFormat: DateFormat = SimpleDateFormat("yy-MM-dd '|' HH:mm", Locale.ENGLISH)
            dateFormat.timeZone = TimeZone.getTimeZone("UTC")

            date?.let {
                try {
                    dateStr = dateFormat.format(Date(date.toLong()))
                } catch (ex: Exception) {
                    Timber.e(ex.message)
                }
            }

            return dateStr
        }
    }
}