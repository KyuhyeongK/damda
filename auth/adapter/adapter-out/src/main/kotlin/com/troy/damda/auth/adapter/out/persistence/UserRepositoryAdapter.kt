package com.troy.damda.auth.adapter.out.persistence

import com.troy.damda.auth.application.domain.User
import com.troy.damda.auth.application.port.out.UserRepositoryPort
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryAdapter(
    private val userJpaRepository: UserJpaRepository,
) : UserRepositoryPort {

    override fun findByUserIdAndPassword(userId: String, password: String): User? {
        return userJpaRepository.findByUserIdAndPassword(userId, password)?.toDomain()
    }

    override fun save(user: User): User {
        return userJpaRepository.save(UserEntity.fromDomain(user)).toDomain()
    }
}