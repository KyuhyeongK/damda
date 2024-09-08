package com.troy.damda.auth.application.port.out

import com.troy.damda.auth.application.domain.Token

interface TokenRepositoryPort {
    fun save(token: Token): Token

    fun findByTokenValue(token: String): Token?
}