package com.troy.damda

enum class YN(val code: String) {
    Y("Y"),
    N("N"),
    ;

    companion object {
        fun fromCode(code: String) = entries.find { it.code == code }
            ?: throw IllegalArgumentException("$code 가 Y/N 이 아님")

        fun of(y: Boolean) = if (y) Y else N
    }
}