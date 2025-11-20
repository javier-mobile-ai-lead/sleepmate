package com.sleepmate.domain.repository

import com.sleepmate.domain.model.VideoItem
import kotlinx.coroutines.flow.Flow

interface VideoRepository {
    fun getVideoRecommendations(): Flow<List<VideoItem>>
    suspend fun refreshVideoRecommendations()
    fun getFavoriteVideoIds(): Flow<Set<String>>
    suspend fun addToFavorites(videoId: String)
    suspend fun removeFromFavorites(videoId: String)
    suspend fun toggleFavorite(videoId: String)
    suspend fun isFavorite(videoId: String): Boolean
}