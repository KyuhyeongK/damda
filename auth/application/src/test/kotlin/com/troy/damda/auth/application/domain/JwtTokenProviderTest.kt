package com.troy.damda.auth.application.domain

import com.troy.damda.DamdaException
import com.troy.damda.DamdaException.ErrorCode
import com.troy.damda.auth.application.service.JwtTokenProvider
import com.troy.damda.auth.application.service.config.JwtProperties
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class JwtTokenProviderTest : StringSpec({

    val secretKey =
        "24c9ca535a15fcfc8059ff186ed2bad65fa5251cded7a440029baaa2362202d20682f3351fe8f00f0905255b2f657ac424d16d36142d59dad3c6461c916bfc6e"
    val userMgmtNo = 1L


    "토큰 만료시간이 현재 이후인 토큰은 검증 성공" {
        val jwtProperties = JwtProperties(
            accessToken = JwtProperties.AccessTokenProperties(secret = secretKey, expiration = 6000000),
            refreshToken = JwtProperties.RefreshTokenProperties(expiration = 6000000)
        )
        val sut = JwtTokenProvider(jwtProperties)
        val token = sut.createAccessToken(userMgmtNo)
        println("token = ${token}")

        sut.getUserMgmtNoFromToken(token) shouldNotBe null
    }

    "만료된 토큰은 예외 발생" {
        val jwtProperties = JwtProperties(
            accessToken = JwtProperties.AccessTokenProperties(secret = secretKey, expiration = -10000),
            refreshToken = JwtProperties.RefreshTokenProperties(expiration = -10000)
        )
        val sut = JwtTokenProvider(jwtProperties)
        val token = sut.createAccessToken(userMgmtNo)

        val err = shouldThrow<DamdaException> { sut.getUserMgmtNoFromToken(token) }
        err.errorCode shouldBe ErrorCode.TOKEN_EXPIRED
    }

    "이상한 토큰을 파싱하는 경우 예외 발생" {
        val jwtProperties = JwtProperties(
            accessToken = JwtProperties.AccessTokenProperties(secret = secretKey, expiration = 6000000),
            refreshToken = JwtProperties.RefreshTokenProperties(expiration = 6000000)
        )
        val sut = JwtTokenProvider(jwtProperties)

        val err = shouldThrow<DamdaException> { sut.getUserMgmtNoFromToken("token") }
        err.errorCode shouldBe ErrorCode.MALFORMED_TOKEN
    }


})
