package com.troy.damda.auth.adapter.out.persistence

import com.troy.damda.auth.application.domain.Token
import com.troy.damda.auth.application.port.out.CreateTokenPort
import com.troy.damda.auth.application.port.out.LoadTokenPort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class TokenRepositoryAdapter(
    private val tokenRepository: TokenRepository,
) : LoadTokenPort, CreateTokenPort {
    override fun save(token: Token): Token {
        return tokenRepository.save(TokenEntity.fromDomain(token)).toDomain()
    }

    override fun findByTokenValue(token: String): Token? {
        return tokenRepository.findByIdOrNull(token)?.toDomain()
    }
}