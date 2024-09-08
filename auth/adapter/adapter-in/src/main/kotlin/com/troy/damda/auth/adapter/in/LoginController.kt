package com.troy.damda.auth.adapter.`in`

import com.troy.damda.auth.application.port.`in`.LoginUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/damda/v1/users")
class LoginController(
    private val loginUseCase: LoginUseCase,
) {

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginUseCase.LoginRequest): ResponseEntity<LoginUseCase.LoginResponse> {
        return ResponseEntity.ok(loginUseCase.login(loginRequest))
    }

}