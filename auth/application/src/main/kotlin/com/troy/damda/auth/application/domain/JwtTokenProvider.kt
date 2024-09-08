package com.troy.damda.auth.application.domain

import com.troy.damda.auth.application.domain.exception.MalformedTokenException
import com.troy.damda.auth.application.domain.exception.TokenAuthException
import com.troy.damda.auth.application.domain.exception.TokenExpiredException
import com.troy.damda.logger
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtTokenProvider(
    @Value("\${jwt.secret}") private val secretKey: String,
    @Value("\${jwt.expiration}") private val expiration: Long,
    @Value("\${jwt.refresh.expiration}") private val refreshExpiration: Long,
) {

    private val hmacShaKey = Keys.hmacShaKeyFor(secretKey.toByteArray(Charsets.UTF_8))
    private val log = logger()

    fun createAccessToken(userMgmtNo: Long): String {
        val now = Date()
        val expirationDate = Date(now.time + expiration)

        val jwtToken = Jwts.builder()
            .subject(userMgmtNo.toString())
            .issuer("damda")
            .issuedAt(now)
            .expiration(expirationDate)
            .signWith(hmacShaKey)
            .compact()

        return jwtToken
    }

    fun createRefreshToken(): String {
        val now = Date()
        val expirationDate = Date(now.time + refreshExpiration)

        val refreshToken = Jwts.builder()
            .issuer("damda")
            .issuedAt(now)
            .expiration(expirationDate)
            .signWith(hmacShaKey)
            .compact()

        return refreshToken
    }

    fun getUserMgmtNoFromToken(token: String): Long {

        log.debug("비밀키, 만료시간 로깅 => {}, {}", secretKey, expiration)

        try {
            val subject = Jwts.parser()
                .verifyWith(hmacShaKey)
                .build()
                .parseSignedClaims(token)
                .payload
                .subject

            log.debug("subject => {}", subject)
            return subject.toLong()

        } catch (e: ExpiredJwtException) {
            throw TokenExpiredException(e)
        } catch (e: MalformedJwtException) {
            throw MalformedTokenException(e)
        } catch (e: Exception) {
            e.printStackTrace()
            throw TokenAuthException(e)
        }

    }

}