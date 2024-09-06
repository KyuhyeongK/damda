package com.troy.damda.auth.adapter.out.persistence.config

import com.troy.damda.auth.application.domain.UserStatus
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter(autoApply = true)
class UserStatusConverter : AttributeConverter<UserStatus, String> {
    override fun convertToDatabaseColumn(attribute: UserStatus?): String {
        return attribute?.code ?: throw IllegalStateException("EventType is null")
    }

    override fun convertToEntityAttribute(column: String?): UserStatus {
        return column?.let { UserStatus.fromCode(column) } ?: throw IllegalStateException("column is null")
    }
}