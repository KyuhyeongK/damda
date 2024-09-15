package com.troy.damda.auth.adapter.out.persistence

import com.troy.damda.auth.application.domain.User
import com.troy.damda.auth.application.port.out.LoadUserPort
import com.troy.damda.auth.application.port.out.CreateUserPort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryAdapter(
    private val userRepository: UserRepository,
) : LoadUserPort, CreateUserPort {

    override fun findByUserMgmtNo(userMgmtNo: Long): User? {
        return userRepository.findByIdOrNull(userMgmtNo)?.toDomain()
    }

    override fun findByUserIdAndPassword(userId: String, password: String): User? {
        return userRepository.findByUserIdAndPassword(userId, password)?.toDomain()
    }

    override fun create(user: User): User {
        return userRepository.save(UserEntity.fromDomain(user)).toDomain()
    }
}