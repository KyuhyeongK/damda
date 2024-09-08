package com.troy.damda.auth.application.port.`in`

interface LoginUseCase {
    fun login(loginRequest: LoginRequest): LoginResponse

    data class LoginRequest(
        val userId: String,
        val password: String,
    )

    data class LoginResponse(
        val accessToken: String,
        val refreshToken: String,
    ) {
        companion object {
            fun empty() = LoginResponse("", "")
        }
    }

}