package com.troy.damda.recordbox.application.domain

import com.troy.damda.recordbox.application.port.out.LoadEventPort
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.ints.shouldBeGreaterThanOrEqual

class EventQueryServiceTest : StringSpec({
    val sut = EventQueryService(object : LoadEventPort {
        override fun findAllByCreatedBy(userMgmtNo: Long): List<Event> {
            return emptyList()
        }
    })

    "이벤트 목록 반환 테스트" {
        val events = sut.getEventsFrom(1)
        events.size shouldBeGreaterThanOrEqual 0
    }
})