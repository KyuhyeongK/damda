package com.troy.damda.auth.application.service.exception

import com.troy.damda.DamdaException

class TokenNeedException(
    message: String? = "Access 토큰이 주어지지 않음",
    cause: Throwable? = null
) : DamdaException("DE9003", message, cause) {
    constructor(cause: Throwable) : this("Access 토큰이 주어지지 않음", cause)
}