package com.troy.damda.recordbox.application.service

import com.troy.damda.recordbox.application.domain.Event
import com.troy.damda.recordbox.application.domain.EventRelationshipType
import com.troy.damda.recordbox.application.domain.EventType
import com.troy.damda.recordbox.application.port.`in`.event.EventQuery.GetEventsRequest
import com.troy.damda.recordbox.application.port.out.event.LoadEventPort
import com.troy.damda.recordbox.application.service.event.EventQueryService
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.longs.shouldBeGreaterThanOrEqual
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import java.time.LocalDate

class EventQueryServiceTest : StringSpec({
    val sut = EventQueryService(object : LoadEventPort {
        override fun findAllByCreatedBy(
            userMgmtNo: Long,
            iqryStartDate: LocalDate?,
            iqryEndDate: LocalDate?,
            eventTypes: List<EventType>?,
            eventRelationshipTypes: List<EventRelationshipType>?,
            pageable: Pageable
        ): Page<Event> {
            return PageImpl(emptyList())
        }

        override fun findById(id: Long): Event? {
            return null
        }
    })

    "이벤트 목록 반환 테스트" {
        val events = sut.getEventsFrom(1, GetEventsRequest(0, 20))
        events.ttcn shouldBeGreaterThanOrEqual 0
    }
})