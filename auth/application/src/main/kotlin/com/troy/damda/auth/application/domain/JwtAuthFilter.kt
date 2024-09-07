package com.troy.damda.auth.application.domain

import com.troy.damda.logger
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class JwtAuthFilter(
    private val jwtTokenProvider: JwtTokenProvider,
) : OncePerRequestFilter() {

    private val log = logger()

    override fun doFilterInternal(
        request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain
    ) {
        log.debug("doFilterInternal!!")


        resolveToken(request)?.let {
            log.debug("Bearer 토큰 => $it")
            val userMgmtNo = jwtTokenProvider.getUserMgmtNoFromToken(it)
            SecurityContextHolder.getContext().authentication = getSpringSecurityAuthenticationFromUserMgmtNo(userMgmtNo)
        }
        filterChain.doFilter(request, response)
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
}