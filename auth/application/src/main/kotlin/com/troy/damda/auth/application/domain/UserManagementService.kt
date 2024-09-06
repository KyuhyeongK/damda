package com.troy.damda.auth.application.domain

import com.troy.damda.auth.application.port.`in`.UserManagementUseCase
import com.troy.damda.auth.application.port.out.UserRepositoryPort
import org.springframework.stereotype.Service

@Service
class UserManagementService(
    private val userRepository: UserRepositoryPort,
) : UserManagementUseCase {

    override fun signUp(request: UserManagementUseCase.SignUpRequest) {
        userRepository.findByUserIdAndPassword(request.userId, request.password)
            ?.let { throw IllegalStateException("User ${request.userId} already exists") }

        userRepository.save(
            User(
                request.nickname, request.userId, request.password, UserStatus.NORMAL
            )
        )
    }
}