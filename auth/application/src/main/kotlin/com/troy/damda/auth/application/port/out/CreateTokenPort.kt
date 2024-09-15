package com.troy.damda.auth.application.port.out

import com.troy.damda.auth.application.domain.Token

interface CreateTokenPort {
    fun save(token: Token): Token
}