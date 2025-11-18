package com.sleepmate.data.remote.api

import com.sleepmate.data.remote.dto.VideoRecommendationDto
import retrofit2.http.GET
import retrofit2.http.Query

interface VideoApi {
    
    @GET("videos/recommendations")
    suspend fun getVideoRecommendations(
        @Query("category") category: String? = null,
        @Query("limit") limit: Int = 20
    ): List<VideoRecommendationDto>
    
    @GET("videos/search")
    suspend fun searchVideos(
        @Query("query") query: String,
        @Query("limit") limit: Int = 20
    ): List<VideoRecommendationDto>
}