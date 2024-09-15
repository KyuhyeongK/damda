package com.troy.damda.auth.application.port.out

import com.troy.damda.auth.application.domain.User

interface CreateUserPort {
    fun save(user: User): User
}