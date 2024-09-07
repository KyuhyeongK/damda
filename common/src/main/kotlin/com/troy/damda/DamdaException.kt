package com.troy.damda

abstract class DamdaException(
    val errorCode: String,
    message: String? = null,
    cause: Throwable? = null
) : RuntimeException(message, cause)