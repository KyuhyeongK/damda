package com.troy.damda.recordbox.adapter.out.persistence

import com.querydsl.jpa.impl.JPAQueryFactory
import com.troy.damda.YN
import com.troy.damda.recordbox.application.domain.PayHistory
import com.troy.damda.recordbox.application.port.out.CreatePayHistoryPort
import com.troy.damda.recordbox.application.port.out.LoadPayHistoryPort
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository

@Repository
class PayHistoryRepositoryAdapter(
    private val payHistoryRepository: PayHistoryRepository,
    private val jpaQueryFactory: JPAQueryFactory,
) : QuerydslRepositorySupport(PayHistoryEntity::class.java), CreatePayHistoryPort, LoadPayHistoryPort {

    override fun findAllByCreatedBy(userMgmtNo: Long, eventId: Long, pageable: Pageable): Page<PayHistory> {
        val payHistory = QPayHistoryEntity.payHistoryEntity

        // 총 건수 조회
        val getCountQuery = jpaQueryFactory.select(payHistory.count())
            .from(payHistory)
            .where(
                payHistory.createdBy.id.eq(userMgmtNo)
                    .and(payHistory.eventId.eq(eventId))
                    .and(
                        payHistory.deleteYn.eq(YN.N)
                    )
            )
        val totalCount = getCountQuery.fetchOne() ?: 0

        // 데이터 조회
        val selectQuery = jpaQueryFactory.selectFrom(payHistory)
            .where(
                payHistory.createdBy.id.eq(userMgmtNo)
                    .and(payHistory.eventId.eq(eventId))
                    .and(payHistory.deleteYn.eq(YN.N))
            )
        val payHistoryResult =
            querydsl?.applyPagination(pageable, selectQuery)?.fetch()?.map { it.toDomain() } ?: emptyList()

        return PageImpl(payHistoryResult, pageable, totalCount)
    }

    override fun create(payHistory: PayHistory): PayHistory {
        return payHistoryRepository.save(PayHistoryEntity.fromDomain(payHistory)).toDomain()
    }

}