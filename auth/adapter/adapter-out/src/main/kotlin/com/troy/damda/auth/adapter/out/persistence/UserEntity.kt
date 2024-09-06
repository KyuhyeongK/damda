package com.troy.damda.auth.adapter.out.persistence

import com.troy.damda.auth.adapter.out.persistence.config.UserStatusConverter
import com.troy.damda.auth.application.domain.User
import com.troy.damda.auth.application.domain.UserStatus
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "user_m")
class UserEntity(
    private val nickname: String,
    private val userId: String,
    private val password: String,

    @Convert(converter = UserStatusConverter::class)
    @Column(name = "user_stcd") private val userStatus: UserStatus,
    private val createdAt: LocalDateTime = LocalDateTime.now(),
    private val updatedAt: LocalDateTime? = null,
    private val deletedAt: LocalDateTime? = null,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_mgmt_no") private val userMgmtNo: Long? = null,
) {
    fun toDomain() = User(
        nickname, userId, password, userStatus, userMgmtNo!!
    )

    companion object {
        fun fromDomain(user: User) = UserEntity(
            user.nickname, user.userId, user.password, user.userStatus
        )
    }
}