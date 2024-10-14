package com.troy.damda.auth.application.service

import com.troy.damda.auth.application.domain.Token
import com.troy.damda.auth.application.port.`in`.LoginUseCase
import com.troy.damda.auth.application.port.`in`.LoginUseCase.*
import com.troy.damda.auth.application.port.out.CreateTokenPort
import com.troy.damda.auth.application.port.out.LoadUserPort
import org.springframework.stereotype.Service

@Service
class LoginService(
    private val loadUserPort: LoadUserPort,
    private val createTokenPort: CreateTokenPort,
    private val tokenProvider: JwtTokenProvider,
) : LoginUseCase {

    override fun login(loginRequest: LoginRequest): LoginResponse {
        val user = loadUserPort.findByUserIdAndPassword(loginRequest.userId, loginRequest.password)
            ?: throw RuntimeException("User with id ${loginRequest.userId} doesn't exist")
        val accessToken = tokenProvider.createAccessToken(user.userMgmtNo!!)
        val refreshToken = tokenProvider.createRefreshToken()

        createTokenPort.create(Token(user.userMgmtNo, refreshToken))

        return LoginResponse(accessToken, refreshToken)
    }
}