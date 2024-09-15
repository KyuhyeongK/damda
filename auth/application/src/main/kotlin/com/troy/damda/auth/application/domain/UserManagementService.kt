package com.troy.damda.auth.application.domain

import com.troy.damda.auth.application.port.`in`.UserManagementUseCase
import com.troy.damda.auth.application.port.out.LoadUserPort
import com.troy.damda.auth.application.port.out.CreateUserPort
import org.springframework.stereotype.Service

@Service
class UserManagementService(
    private val loadUserPort: LoadUserPort,
    private val createUserPort: CreateUserPort,
) : UserManagementUseCase {

    override fun signUp(request: UserManagementUseCase.SignUpRequest) {
        loadUserPort.findByUserIdAndPassword(request.userId, request.password)
            ?.let { throw IllegalStateException("User ${request.userId} already exists") }

        createUserPort.create(
            User(
                request.nickname, request.userId, request.password, UserStatus.NORMAL
            )
        )
    }
}