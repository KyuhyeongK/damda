package com.troy.damda.auth.application.port.out

import com.troy.damda.auth.application.domain.User

interface LoadUserPort {
    fun findByUserMgmtNo(userMgmtNo: Long): User?

    fun findByUserIdAndPassword(userId: String, password: String): User?
}