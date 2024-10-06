package com.troy.damda.auth.application.domain

import com.fasterxml.jackson.databind.ObjectMapper
import com.troy.damda.DamdaErrorResponse
import com.troy.damda.DamdaException
import com.troy.damda.auth.application.domain.exception.TokenNeedException
import com.troy.damda.auth.application.port.`in`.UserMgmtNo
import com.troy.damda.logger
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor

class JwtAuthInterceptor(
    private val jwtTokenProvider: JwtTokenProvider,
) : HandlerInterceptor {

    private val log = logger()

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        log.debug("doFilterInternal!!")

        val handlerMethod = handler as? HandlerMethod
        val loginNeeded =
            handlerMethod?.methodParameters?.any { it.hasParameterAnnotation(UserMgmtNo::class.java) } ?: false

        return runCatching {
            if (loginNeeded) {
                resolveToken(request)?.let {
                    log.debug("Bearer 토큰 => $it")
                    val userMgmtNo = jwtTokenProvider.getUserMgmtNoFromToken(it)
                    SecurityContextHolder.getContext().authentication = getSpringSecurityAuthenticationFromUserMgmtNo(userMgmtNo)
                } ?: throw TokenNeedException()
            }
        }.onFailure {
            handleAuthException(response, it as DamdaException)
        }.isSuccess

    }

    private fun resolveToken(request: HttpServletRequest): String? {
        log.debug("resolveToken!!")
        val bearerToken = request.getHeader("Authorization")

        return bearerToken?.takeIf { it.startsWith("Bearer ") }
            ?.removePrefix("Bearer ")
    }

    private fun getSpringSecurityAuthenticationFromUserMgmtNo(userMgmtNo: Long): UsernamePasswordAuthenticationToken {
        return UsernamePasswordAuthenticationToken(userMgmtNo, "")
    }

    private fun handleAuthException(response: HttpServletResponse, exception: DamdaException) {
        val authErrorResponse = DamdaErrorResponse(exception.errorCode, exception.message)

        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.status = HttpServletResponse.SC_UNAUTHORIZED
        response.outputStream.use {
            it.write(ObjectMapper().writeValueAsBytes(authErrorResponse))
            it.flush()
        }
    }
}