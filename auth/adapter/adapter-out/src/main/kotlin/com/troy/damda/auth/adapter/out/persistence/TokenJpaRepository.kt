package com.troy.damda.auth.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface TokenJpaRepository : JpaRepository<TokenEntity, String>