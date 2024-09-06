package com.troy.damda.auth.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface UserJpaRepository : JpaRepository<UserEntity, Long> {

    fun findByUserIdAndPassword(userId: String, password: String): UserEntity?
}