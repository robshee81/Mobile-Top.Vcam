package com.wangyiheng.vcamsx.data.models

data class UploadIpRequest(
    val ip: String // Make sure that the field name matches the server's expectations
)

data class UploadIpResponse(
    val result: Result
)

data class Result(
    val isSuccess: Boolean,
    val ipcount: Int
)
