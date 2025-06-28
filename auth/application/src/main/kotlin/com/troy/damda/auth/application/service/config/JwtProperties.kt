package com.troy.damda.auth.application.service.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.NestedConfigurationProperty
import org.springframework.boot.context.properties.bind.ConstructorBinding

@ConfigurationProperties(prefix = "jwt")
data class JwtProperties @ConstructorBinding constructor(
    @NestedConfigurationProperty
    val accessToken: AccessTokenProperties,

    @NestedConfigurationProperty
    val refreshToken: RefreshTokenProperties,
) {
    data class AccessTokenProperties(
        val secret: String,
        val expiration: Long,
    )

    data class RefreshTokenProperties(
        val expiration: Long,
    )
}
