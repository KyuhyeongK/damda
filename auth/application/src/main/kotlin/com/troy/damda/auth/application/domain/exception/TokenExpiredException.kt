package com.troy.damda.auth.application.domain.exception

import com.troy.damda.DamdaException

class TokenExpiredException(
    message: String? = "만료된 토큰임",
    cause: Throwable? = null
) : DamdaException("DE9002", message, cause) {
    constructor(cause: Throwable) : this("만료된 토큰임", cause)
}