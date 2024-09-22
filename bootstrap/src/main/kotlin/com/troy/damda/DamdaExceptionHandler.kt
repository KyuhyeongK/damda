package com.troy.damda

import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice(basePackages = ["com.troy"])
class DamdaExceptionHandler {

    private val log = logger()

    @ExceptionHandler(DamdaException::class)
    protected fun handleDamdaException(e: DamdaException, response: HttpServletResponse): DamdaErrorResponse {
        log.debug("예외 발생 => {}", e.message)
        response.status = HttpStatus.INTERNAL_SERVER_ERROR.value()

        return DamdaErrorResponse(e.errorCode, e.message)
    }

    @ExceptionHandler(Exception::class)
    protected fun handleDefaultExceptions(exception: Exception, response: HttpServletResponse): DamdaErrorResponse {
        log.warn("지원하지 않는 시스템오류 발생: ${exception.message}", exception)
        response.status = HttpStatus.INTERNAL_SERVER_ERROR.value()

        return DamdaErrorResponse(
            code = "DE9999", message = "An unexpected error occurred"
        )
    }

}
