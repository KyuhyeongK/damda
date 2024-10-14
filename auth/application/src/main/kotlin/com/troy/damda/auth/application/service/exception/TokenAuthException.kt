package com.troy.damda.auth.application.service.exception

import com.troy.damda.DamdaException

class TokenAuthException(
    message: String? = "토큰 인증 실패 기타오류",
    cause: Throwable? = null
) : DamdaException("DE9999", message, cause) {
    constructor(cause: Throwable) : this("토큰 인증 실패 기타오류", cause)
}