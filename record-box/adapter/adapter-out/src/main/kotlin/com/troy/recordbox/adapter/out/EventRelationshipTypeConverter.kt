package com.troy.recordbox.adapter.out

import com.troy.recordbox.application.domain.EventRelationshipType
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter(autoApply = true)
class EventRelationshipTypeConverter : AttributeConverter<EventRelationshipType, String> {
    override fun convertToDatabaseColumn(attribute: EventRelationshipType?): String {
        return attribute?.code ?: throw IllegalStateException("EventRelationshipType is null")
    }

    override fun convertToEntityAttribute(column: String?): EventRelationshipType {
        return column?.let { EventRelationshipType.fromCode(column) } ?: throw IllegalStateException("column is null")
    }
}