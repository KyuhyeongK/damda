package com.troy.damda.auth.adapter.`in`.security

import com.troy.damda.logger
import org.springframework.core.MethodParameter
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

class UserMgmtNoArgumentResolver : HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.getParameterAnnotation(UserMgmtNo::class.java) != null
    }

    private val log = logger()

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any? {

        val auth = SecurityContextHolder.getContext().authentication
        log.debug("@UserMgmtNo 어노테이션의 리턴값 => {}", auth.principal)
        return auth.principal
    }
}