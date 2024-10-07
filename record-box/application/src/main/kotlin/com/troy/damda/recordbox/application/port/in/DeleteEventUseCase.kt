package com.troy.damda.recordbox.application.port.`in`

interface DeleteEventUseCase {

    fun deleteEvent(userMgmtNo: Long, eventId: Long)

}