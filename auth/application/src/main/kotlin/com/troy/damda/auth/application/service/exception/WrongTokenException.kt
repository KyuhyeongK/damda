package com.troy.damda.auth.application.service.exception

import com.troy.damda.DamdaException

class WrongTokenException(
    message: String? = "잘못된 형식의 토큰임",
    cause: Throwable? = null,
) : DamdaException("DE9001", message, cause) {
    constructor(cause: Throwable) : this("잘못된 형식의 토큰임", cause)
}