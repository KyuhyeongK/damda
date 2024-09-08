package com.troy.damda.auth.application.domain

import com.troy.damda.auth.application.port.`in`.LoginUseCase
import com.troy.damda.auth.application.port.out.TokenRepositoryPort
import com.troy.damda.auth.application.port.out.UserRepositoryPort
import org.springframework.stereotype.Service

@Service
class LoginService(
    private val userRepository: UserRepositoryPort,
    private val tokenRepository: TokenRepositoryPort,
    private val tokenProvider: JwtTokenProvider,
) : LoginUseCase {

    override fun login(loginRequest: LoginUseCase.LoginRequest): LoginUseCase.LoginResponse {
        val user = userRepository.findByUserIdAndPassword(loginRequest.userId, loginRequest.password)
            ?: throw RuntimeException("User with id ${loginRequest.userId} doesn't exist")
        val accessToken = tokenProvider.createAccessToken(user.userMgmtNo!!)
        val refreshToken = tokenProvider.createRefreshToken()

        tokenRepository.save(Token(user.userMgmtNo, refreshToken))

        return LoginUseCase.LoginResponse(accessToken, refreshToken)
    }
}