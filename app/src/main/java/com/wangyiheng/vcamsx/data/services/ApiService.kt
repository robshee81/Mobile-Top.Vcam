package com.wangyiheng.vcamsx.data.services;

import com.wangyiheng.vcamsx.data.models.UploadIpRequest
import com.wangyiheng.vcamsx.data.models.UploadIpResponse
import retrofit2.Response;
import retrofit2.http.*;

// Define the interface that interacts with the back-end API
interface ApiService {
    @POST("/")
    suspend fun uploadIp(@Body data: UploadIpRequest):Response<UploadIpResponse>
}
