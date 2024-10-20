package com.troy.damda.auth.application.service

import com.troy.damda.auth.application.domain.User
import com.troy.damda.auth.application.domain.UserStatus
import com.troy.damda.auth.application.port.`in`.SignUpUseCase
import com.troy.damda.auth.application.port.`in`.SignUpUseCase.*
import com.troy.damda.auth.application.port.out.LoadUserPort
import com.troy.damda.auth.application.port.out.CreateUserPort
import org.springframework.stereotype.Service

@Service
class SignUpService(
    private val loadUserPort: LoadUserPort,
    private val createUserPort: CreateUserPort,
) : SignUpUseCase {

    override fun signUp(request: SignUpRequest) {
        loadUserPort.findByUserIdAndPassword(request.userId, request.password)
            ?.let { throw IllegalStateException("User ${request.userId} already exists") }

        createUserPort.create(
            User(
                request.nickname, request.userId, request.password, UserStatus.NORMAL
            )
        )
    }
}