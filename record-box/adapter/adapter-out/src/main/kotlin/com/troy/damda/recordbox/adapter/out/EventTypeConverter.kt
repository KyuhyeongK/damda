package com.troy.damda.recordbox.adapter.out

import com.troy.damda.recordbox.application.domain.EventType
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter(autoApply = true)
class EventTypeConverter : AttributeConverter<EventType, String> {
    override fun convertToDatabaseColumn(attribute: EventType?): String {
        return attribute?.code ?: throw IllegalStateException("EventType is null")
    }

    override fun convertToEntityAttribute(column: String?): EventType {
        return column?.let { EventType.fromCode(column) } ?: throw IllegalStateException("column is null")
    }
}