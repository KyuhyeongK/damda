package com.troy.damda.auth.adapter.`in`

import com.troy.damda.DamdaResponse
import com.troy.damda.auth.application.port.`in`.SignUpUseCase
import com.troy.damda.auth.application.port.`in`.SignUpUseCase.*
import com.troy.damda.logger
import com.troy.damda.ok
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/damda/v1/auth/users")
class SignUpController(
    private val signUpUseCase: SignUpUseCase,
) {

    private val log = logger()

    @PostMapping("/signup")
    fun signUp(@RequestBody request: SignUpRequest): DamdaResponse<Unit> {
        log.debug("회원가입 요청 Dto => {}", request)
        signUpUseCase.signUp(request)
        return ok(Unit)
    }

}