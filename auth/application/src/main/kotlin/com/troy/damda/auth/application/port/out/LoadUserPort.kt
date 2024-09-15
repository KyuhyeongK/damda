package com.troy.damda.auth.application.port.out

import com.troy.damda.auth.application.domain.User

interface LoadUserPort {
    fun findByUserIdAndPassword(userId: String, password: String): User?
}