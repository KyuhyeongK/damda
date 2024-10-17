package com.troy.damda.recordbox.adapter.out.persistence.config

import com.troy.damda.recordbox.application.domain.PayType
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter(autoApply = true)
class PayTypeConverter : AttributeConverter<PayType, String> {
    override fun convertToDatabaseColumn(attribute: PayType?): String {
        return attribute?.code ?: throw IllegalStateException("EventType is null")
    }

    override fun convertToEntityAttribute(column: String?): PayType {
        return column?.let { PayType.fromCode(column) } ?: throw IllegalStateException("column is null")
    }
}