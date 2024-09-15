package com.troy.damda.recordbox.adapter.out.persistence

import com.troy.damda.recordbox.application.domain.User
import jakarta.persistence.*

@Entity
@Table(name = "user_m")
class EventUserEntity(
    private val nickname: String,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_mgmt_no") val id: Long?,
) {
    companion object {
        fun fromDomain(user: User) = EventUserEntity(user.nickname, user.id)
    }

    fun toDomain() = User(nickname, id!!)
}