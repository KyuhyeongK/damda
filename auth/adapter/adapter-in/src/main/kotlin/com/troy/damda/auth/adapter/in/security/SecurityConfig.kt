package com.troy.damda.auth.adapter.`in`.security

import com.troy.damda.auth.application.domain.JwtAuthInterceptor
import com.troy.damda.auth.application.domain.JwtTokenProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtTokenProvider: JwtTokenProvider,
) : WebMvcConfigurer {

    @Bean
    fun jwtAuthInterceptor(): JwtAuthInterceptor {
        return JwtAuthInterceptor(jwtTokenProvider)
    }

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .httpBasic { it.disable() }
            .csrf { it.disable() }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authorizeHttpRequests {
                it.anyRequest()
                    .permitAll()
            }
            .build()
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(jwtAuthInterceptor())
    }

    @Bean
    fun userMgmtNoArgumentResolver(): UserMgmtNoArgumentResolver {
        return UserMgmtNoArgumentResolver()
    }

    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(userMgmtNoArgumentResolver())
    }
}