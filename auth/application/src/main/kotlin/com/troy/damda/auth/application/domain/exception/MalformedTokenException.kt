package com.troy.damda.auth.application.domain.exception

import com.troy.damda.DamdaException

class MalformedTokenException(
    message: String? = "잘못된 형식의 토큰임",
    cause: Throwable? = null
) : DamdaException("DB9099") {
    constructor(cause: Throwable) : this("잘못된 형식의 토큰임", cause)
}