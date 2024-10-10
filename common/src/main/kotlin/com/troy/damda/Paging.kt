package com.troy.damda

data class PagingRequest(
    val pageNo: Int,
    val pageSize: Int,
)

data class PagingResult<T>(
    val pageNo: Int,
    val pageSize: Int,
    val ttcn: Long,
    val nextPageExisYN: YN,
    val contents: List<T>,
)