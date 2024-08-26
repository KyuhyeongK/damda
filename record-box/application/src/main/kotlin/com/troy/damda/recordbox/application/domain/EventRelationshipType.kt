package com.troy.damda.recordbox.application.domain

enum class EventRelationshipType(val code: String) {
    SELF("00"),
    FAMILY("01"),
    FRIEND("02"),
    ;

    companion object {
        fun fromCode(code: String) = entries.find { it.code == code }
            ?: throw IllegalArgumentException("$code 와 일치하는 유효값 없음")
    }
}
