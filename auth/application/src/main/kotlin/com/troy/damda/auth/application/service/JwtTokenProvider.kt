package com.troy.damda.auth.application.service

import com.troy.damda.DamdaException
import com.troy.damda.DamdaException.ErrorCode
import com.troy.damda.YamlPropertySourceFactory
import com.troy.damda.auth.application.service.config.JwtProperties
import com.troy.damda.logger
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.security.Keys
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import java.security.SignatureException
import java.util.*

@Configuration
@PropertySource("classpath:jwt-\${spring.profiles.active}.yaml", factory = YamlPropertySourceFactory::class)
@EnableConfigurationProperties(JwtProperties::class)
class JwtTokenProvider(
    private val jwtProperties: JwtProperties,
) {

    private val hmacShaKey = Keys.hmacShaKeyFor(jwtProperties.accessToken.secret.toByteArray(Charsets.UTF_8))
    private val log = logger()

    fun createAccessToken(userMgmtNo: Long): String {
        val now = Date()
        val expirationDate = Date(now.time + jwtProperties.accessToken.expiration)

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
        val expirationDate = Date(now.time + jwtProperties.refreshToken.expiration)

        val refreshToken = Jwts.builder()
            .issuer("damda")
            .issuedAt(now)
            .expiration(expirationDate)
            .signWith(hmacShaKey)
            .compact()

        return refreshToken
    }

    fun getUserMgmtNoFromToken(token: String): Long {

        log.debug("비밀키, 만료시간 로깅 => {}, {}", jwtProperties.accessToken.secret, jwtProperties.accessToken.expiration)

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
            throw DamdaException(ErrorCode.TOKEN_EXPIRED, cause = e)
        } catch (e: MalformedJwtException) {
            throw DamdaException(ErrorCode.MALFORMED_TOKEN, cause = e)
        } catch (e: SignatureException) {
            throw DamdaException(ErrorCode.WRONG_TOKEN, cause = e)
        } catch (e: Exception) {
            throw DamdaException(ErrorCode.TOKEN_AUTH, cause = e)
        }

    }

}