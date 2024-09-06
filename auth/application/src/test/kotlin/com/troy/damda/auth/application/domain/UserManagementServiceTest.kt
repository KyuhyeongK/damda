package com.troy.damda.auth.application.domain

import com.troy.damda.auth.application.port.`in`.UserManagementUseCase
import com.troy.damda.auth.application.port.out.UserRepositoryPort
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk

class UserManagementServiceTest : BehaviorSpec({

    val userRepository = mockk<UserRepositoryPort>()
    val sut = UserManagementService(userRepository)

    given("회원가입 Dto가 주어졌을때") {
        val nickname = "testUser"
        val userId = "1111"
        val password = "pswd"
        val request = UserManagementUseCase.SignUpRequest(nickname, userId, password)

        `when`("이미 존재하는 회원이라면") {
            every { userRepository.findByUserIdAndPassword(userId, password) } returns User(
                nickname,
                userId,
                password,
                UserStatus.NORMAL
            )

            then("예외를 던진다") {
                shouldThrow<IllegalStateException> {
                    sut.signUp(request)
                }
            }
        }

        `when`("존재하지 않는 회원이라면") {
            every { userRepository.findByUserIdAndPassword(userId, password) } returns null
            every { userRepository.save(any()) } returns User(
                nickname,
                userId,
                password,
                UserStatus.NORMAL
            )

            then("회원가입이 정상적으로 이루어진다") {
                sut.signUp(request)
            }
        }

    }
})
