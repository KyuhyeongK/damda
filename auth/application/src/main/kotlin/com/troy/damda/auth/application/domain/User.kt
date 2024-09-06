package com.troy.damda.auth.application.domain

class User(
    val nickname: String,
    val userId: String,
    val password: String,
    val userStatus: UserStatus,
    val userMgmtNo: Long? = null,
)