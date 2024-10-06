package com.troy.damda.auth.adapter.`in`

import com.troy.damda.DamdaResponse
import com.troy.damda.auth.application.port.`in`.LoginUseCase
import com.troy.damda.ok
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/damda/v1/auth/users")
class LoginController(
    private val loginUseCase: LoginUseCase,
) {

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginUseCase.LoginRequest): DamdaResponse<LoginUseCase.LoginResponse> {
        return ok(loginUseCase.login(loginRequest))
    }

}