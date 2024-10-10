package com.troy.damda.recordbox.adapter.out.persistence

import com.querydsl.jpa.impl.JPAQueryFactory
import com.troy.damda.YN
import com.troy.damda.recordbox.application.domain.Event
import com.troy.damda.recordbox.application.port.out.CreateEventPort
import com.troy.damda.recordbox.application.port.out.DeleteEventPort
import com.troy.damda.recordbox.application.port.out.LoadEventPort
import com.troy.damda.recordbox.application.port.out.UpdateEventPort
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
class EventRepositoryAdapter(
    private val eventRepository: EventRepository,
    private val jpaQueryFactory: JPAQueryFactory,
) : QuerydslRepositorySupport(EventEntity::class.java), LoadEventPort, CreateEventPort, UpdateEventPort,
    DeleteEventPort {

    override fun findAllByCreatedBy(
        userMgmtNo: Long,
        iqryStartDate: LocalDate?,
        iqryEndDate: LocalDate?,
        pageable: Pageable
    ): Page<Event> {
        val event = QEventEntity.eventEntity

        // 총 건수 조회
        val countQuery = jpaQueryFactory.select(event.count())
            .from(event)
            .where(
                event.createdBy.id.eq(userMgmtNo)
                    .and(event.deleteYn.eq(YN.N))
                    .and(iqryStartDate?.let { event.eventDate.goe(it) })
                    .and(iqryEndDate?.let { event.eventDate.loe(it) })
            )
        val totalCount = countQuery.fetchOne() ?: 0

        // 데이터 조회
        val selectQuery = jpaQueryFactory.selectFrom(event)
            .where(
                event.createdBy.id.eq(userMgmtNo)
                    .and(event.deleteYn.eq(YN.N))
                    .and(iqryStartDate?.let { event.eventDate.goe(it) })
                    .and(iqryEndDate?.let { event.eventDate.loe(it) })
            )
        val events = querydsl?.applyPagination(pageable, selectQuery)?.fetch()?.map { it.toDomain() } ?: emptyList()

        return PageImpl(events, pageable, totalCount)
    }

    override fun findById(id: Long): Event? {
        return eventRepository.findByIdOrNull(id)?.toDomain()
    }

    override fun create(event: Event): Event {
        return eventRepository.save(EventEntity.fromDomain(event)).toDomain()
    }

    override fun update(event: Event): Event {
        return eventRepository.save(EventEntity.fromDomain(event)).toDomain()
    }

    override fun delete(event: Event) {
        eventRepository.save(EventEntity.fromDomain(event))
    }
}