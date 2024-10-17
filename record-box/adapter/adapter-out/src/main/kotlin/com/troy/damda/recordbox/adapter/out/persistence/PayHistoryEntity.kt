package com.troy.damda.recordbox.adapter.out.persistence

import com.troy.damda.YN
import com.troy.damda.recordbox.adapter.out.persistence.config.DeleteYNConverter
import com.troy.damda.recordbox.adapter.out.persistence.config.PayTypeConverter
import com.troy.damda.recordbox.application.domain.Event
import com.troy.damda.recordbox.application.domain.PayHistory
import com.troy.damda.recordbox.application.domain.PayType
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "payhistory_l")
class PayHistoryEntity(

    @Convert(converter = PayTypeConverter::class)
    @Column(name = "pay_type_cd") private val type: PayType,

    @ManyToOne @JoinColumn(name = "createdBy")
    private val createdBy: EventUserEntity,

    private val payAmount: BigDecimal,

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "event_id")
//    private val event: EventEntity,
    private val eventId: Long,

    private val createdAt: LocalDateTime = LocalDateTime.now(),
    private val updatedAt: LocalDateTime?,
    @Convert(converter = DeleteYNConverter::class)
    @Column(name = "delete_yn") private val deleteYn: YN?,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payhistory_id") private val id: Long? = null
) {

    companion object {
        fun fromDomain(payHistory: PayHistory) = PayHistoryEntity(
            type = payHistory.type,
            createdBy = EventUserEntity.fromDomain(payHistory.createdBy),
            payAmount = payHistory.payAmount,
            eventId = payHistory.eventId,
            createdAt = payHistory.createdAt,
            updatedAt = payHistory.updatedAt,
            deleteYn = payHistory.deleteYN,
            id = payHistory.id
        )
    }

    fun toDomain() = PayHistory(
        type = type,
        createdBy = createdBy.toDomain(),
        payAmount = payAmount,
        eventId = eventId,
        createdAt = createdAt,
        updatedAt = updatedAt,
        deleteYN = deleteYn,
        id = id
    )
}