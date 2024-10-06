package com.troy.damda

data class DamdaErrorResponse(
    val code: String,
    val message: String?,
)

data class DamdaResponse<T>(
    val code: String,
    val message: String?,
    val result: T,
)

fun <T> ok(result: T): DamdaResponse<T> {
    return DamdaResponse("DE0000", "성공", result)
}