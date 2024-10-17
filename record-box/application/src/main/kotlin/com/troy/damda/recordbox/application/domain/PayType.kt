package com.troy.damda.recordbox.application.domain

enum class PayType(val code: String) {
    WITHDRAWAL("W"),    // 지급
    DEPOSIT("D"),       // 입금
    ;

    companion object {
        fun fromCode(code: String) = entries.find { it.code == code }
            ?: throw IllegalArgumentException("$code 와 일치하는 유효값 없음")
    }
}
