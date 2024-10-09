package com.troy.damda

data class PagingInfo(
    val pageNo: Int,
    val pageSize: Int,
    val ttcn: Long,
    val nextPageExisYN: YN,
)