package com.alifjafar.mynews.base

data class BaseResponse<T> (
    val status: String,
    val totalResult: Int,
    val articles: T
)