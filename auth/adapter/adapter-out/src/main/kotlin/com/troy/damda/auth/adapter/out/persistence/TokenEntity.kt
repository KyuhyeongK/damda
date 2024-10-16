package com.troy.damda.auth.adapter.out.persistence

import com.troy.damda.auth.application.domain.Token
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "token_m")
class TokenEntity(
    private val userMgmtNo: Long,
    @Id @Column(name = "token_value") private val id: String? = null,
    // todo 토큰 생성시간, 만료시간 컬럼 추가 필요
) {
    fun toDomain() = Token(userMgmtNo, id!!)

    companion object {
        fun fromDomain(token: Token) = TokenEntity(token.userMgmtNo, token.tokenValue)
    }
}