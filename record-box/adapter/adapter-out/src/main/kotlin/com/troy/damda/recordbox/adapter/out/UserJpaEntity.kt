package com.troy.damda.recordbox.adapter.out

import com.troy.damda.recordbox.application.domain.User
import jakarta.persistence.*

@Entity
@Table(name = "user_m")
class UserJpaEntity(
    private val nickname: String,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "user_id") val id: Long?,
) {

    fun toDomain() = User(nickname, id!!)
}