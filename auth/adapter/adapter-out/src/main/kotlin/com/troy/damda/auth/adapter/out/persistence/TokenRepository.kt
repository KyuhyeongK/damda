package com.troy.damda.auth.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface TokenRepository : JpaRepository<TokenEntity, String>