package com.troy.damda.recordbox.adapter.out.persistence.config

import com.troy.damda.YN
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter(autoApply = true)
class DeleteYNConverter : AttributeConverter<YN, String> {
    override fun convertToDatabaseColumn(attribute: YN?): String {
        return attribute?.code ?: "N"
    }

    override fun convertToEntityAttribute(column: String?): YN {
        return column?.let { YN.fromCode(column) } ?: YN.N
    }
}