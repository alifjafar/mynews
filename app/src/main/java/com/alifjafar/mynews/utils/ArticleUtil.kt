package com.alifjafar.mynews.utils

import java.text.SimpleDateFormat
import java.util.*

class ArticleUtil {

    fun makeExcerpt(data: String, limitChar: Int): String {
        return if (data.length > limitChar) "${data.substring(0, limitChar)} ..." else data
    }

    fun formatDate(stringDate: String?): String {
        val dateFormatted = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault()).parse(stringDate)
        return SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss", Locale.getDefault()).format(dateFormatted)
    }
}