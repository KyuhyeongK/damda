package com.troy.damda.recordbox.application.port.`in`.event

interface DeleteEventUseCase {

    fun deleteEvent(userMgmtNo: Long, eventId: Long)

}