package com.troy.damda.auth.application.port.out

import com.troy.damda.auth.application.domain.User

interface UserRepositoryPort {
    fun findByUserIdAndPassword(userId: String, password: String): User?

    fun save(user: User): User
}