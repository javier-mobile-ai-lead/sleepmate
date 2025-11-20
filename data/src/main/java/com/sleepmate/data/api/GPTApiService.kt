package com.sleepmate.data.api

import com.sleepmate.data.model.GPTRequest
import com.sleepmate.data.model.GPTResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface GPTApiService {
    
    @POST("v1/chat/completions")
    suspend fun sendMessage(
        @Header("Authorization") authorization: String,
        @Header("Content-Type") contentType: String = "application/json",
        @Body request: GPTRequest
    ): Response<GPTResponse>
}