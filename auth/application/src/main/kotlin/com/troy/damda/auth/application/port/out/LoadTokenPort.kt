package com.troy.damda.auth.application.port.out

import com.troy.damda.auth.application.domain.Token

interface LoadTokenPort {
    fun findByTokenValue(token: String): Token?
}