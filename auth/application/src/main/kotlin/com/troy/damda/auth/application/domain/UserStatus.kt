package com.troy.damda.auth.application.domain

enum class UserStatus(val code: String) {
    NORMAL("00"),
    DORMANT("09"),
    TERMINATED("99")
    ;

    companion object {
        fun fromCode(code: String) = entries.find { it.code == code }
            ?: throw IllegalArgumentException("$code 와 일치하는 유효값 없음")
    }
}