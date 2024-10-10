package com.troy.damda.recordbox.application.domain

import com.troy.damda.PagingRequest
import com.troy.damda.recordbox.application.port.out.LoadEventPort
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.longs.shouldBeGreaterThanOrEqual
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable

class EventQueryServiceTest : StringSpec({
    val sut = EventQueryService(object : LoadEventPort {
        override fun findAllByCreatedBy(userMgmtNo: Long, pageable: Pageable): Page<Event> {
            return PageImpl(emptyList())
        }

        override fun findById(id: Long): Event? {
            return null
        }
    })

    "이벤트 목록 반환 테스트" {
        val events = sut.getEventsFrom(1, PagingRequest(0, 10))
        events.ttcn shouldBeGreaterThanOrEqual 0
    }
})