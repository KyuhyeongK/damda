package com.troy.damda.auth.adapter.`in`

import com.troy.damda.DamdaResponse
import com.troy.damda.auth.application.port.`in`.UserManagementUseCase
import com.troy.damda.logger
import com.troy.damda.ok
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/damda/v1/auth/users")
class UserManagementController(
    private val userManagementUseCase: UserManagementUseCase,
) {

    private val log = logger()

    @PostMapping("/signup")
    fun signUp(@RequestBody request: UserManagementUseCase.SignUpRequest): DamdaResponse<Unit> {
        log.debug("회원가입 요청 Dto => {}", request)
        userManagementUseCase.signUp(request)
        return ok(Unit)
    }

}