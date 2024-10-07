package com.troy.damda

import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.NoHandlerFoundException

@RestControllerAdvice
class DamdaExceptionHandler {

    private val log = logger()

    @ExceptionHandler(DamdaException::class)
    protected fun handleDamdaException(e: DamdaException, response: HttpServletResponse): DamdaErrorResponse {
        log.debug("예외 발생 => {}", e.message)
        response.status = HttpStatus.INTERNAL_SERVER_ERROR.value()

        return DamdaErrorResponse(e.errorCode, e.message)
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected fun handleDefaultException(exception: Exception): DamdaErrorResponse {
        val message = exception.message
        log.warn("지원하지 않는 시스템오류 발생: $message", exception)

        return DamdaErrorResponse(
            code = "DE9999", message = "An unexpected error occurred: $message"
        )
    }

    @ExceptionHandler(NoHandlerFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected fun handleNotFoundException(e: NoHandlerFoundException): DamdaErrorResponse {
        log.debug("지원하지 않는 api 경로! (404) => {}", e.message)
        return DamdaErrorResponse(
            code = "DE9404", message = "404 Not Found"
        )
    }
}
