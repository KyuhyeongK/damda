package com.troy.damda.recordbox.application.domain

enum class EventType(val code: String) {
    WEDDING("10"),
    BIRTH("11"),
    BIRTHDAY("12"),
    FUNERAL("20"),
    ETC("99"),
    ;

    companion object {
        fun fromCode(code: String) = entries.find { it.code == code }
            ?: throw IllegalArgumentException("$code 와 일치하는 유효값 없음")
    }
}
