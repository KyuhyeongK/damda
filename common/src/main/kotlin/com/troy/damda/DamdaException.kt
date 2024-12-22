package com.troy.damda

class DamdaException(
    val errorCode: ErrorCode,
    extraMessage: String? = null,
    cause: Throwable? = null
) : RuntimeException(extraMessage, cause) {
    val errorMessage = if (extraMessage != null) {
        "${errorCode.message} : $extraMessage"
    } else {
        errorCode.message
    }

    enum class ErrorCode(val code: String, val message: String) {
        // 9XXX : 인증
        MALFORMED_TOKEN("DB9099", "잘못된 형식의 토큰임"),
        TOKEN_AUTH("DE9999", "토큰 인증 실패 기타오류"),
        WRONG_TOKEN("DE9001", "잘못된 형식의 토큰임"),
        TOKEN_EXPIRED("DE9002", "만료된 토큰임"),
        TOKEN_NEED("DE9003", "Access 토큰이 주어지지 않음"),
        USER_MGMT_NO_NOT_FOUND("DE9004", "사관번호가 존재하지 않음"),
        USER_MGMT_NO_MISMATCH("DE9005", "사관번호 불일치"),
        USER_ID_NOT_FOUND("DE9006", "사용자아이디가 존재하지 않음"),

        // 8XXX : record-box
        EVENT_ID_MISMATCH("DE8001", "이벤트ID 불일치"),
        EVEN_NOT_FOUND("DE8002", "이벤트가 존재하지 않음"),
        PAY_HISTORY_NOT_FOUND("DE8003", "납부내역이 존재하지 않음"),
    }
}