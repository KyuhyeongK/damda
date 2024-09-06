package com.troy.damda.auth.application.port.`in`

interface UserManagementUseCase {

    fun signUp(request: SignUpRequest)

    data class SignUpRequest(
        val nickname: String,
        val userId: String,
        val password: String,
    )

}