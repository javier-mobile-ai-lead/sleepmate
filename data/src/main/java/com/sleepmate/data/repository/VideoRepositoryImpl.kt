package com.sleepmate.data.repository

import com.sleepmate.data.datasource.local.VideoFavoritesDataStore
import com.sleepmate.data.datasource.remote.VideoRemoteDataSource
import com.sleepmate.domain.model.VideoItem
import com.sleepmate.domain.repository.VideoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VideoRepositoryImpl @Inject constructor(
    private val remoteDataSource: VideoRemoteDataSource,
    private val favoritesDataStore: VideoFavoritesDataStore
) : VideoRepository {
    
    override fun getVideoRecommendations(): Flow<List<VideoItem>> {
        return remoteDataSource.getVideoRecommendations()
            .catch { exception ->
                Timber.e(exception, "Error getting video recommendations")
                emit(emptyList())
            }
    }
    
    override suspend fun refreshVideoRecommendations() {
        try {
            remoteDataSource.refreshVideoRecommendations()
        } catch (e: Exception) {
            Timber.e(e, "Error refreshing video recommendations")
            throw e
        }
    }

    override fun getFavoriteVideoIds(): Flow<Set<String>> {
        return favoritesDataStore.getFavoriteVideoIds()
    }

    override suspend fun addToFavorites(videoId: String) {
        favoritesDataStore.addToFavorites(videoId)
    }

    override suspend fun removeFromFavorites(videoId: String) {
        favoritesDataStore.removeFromFavorites(videoId)
    }

    override suspend fun toggleFavorite(videoId: String) {
        favoritesDataStore.toggleFavorite(videoId)
    }

    override suspend fun isFavorite(videoId: String): Boolean {
        return getFavoriteVideoIds().first().contains(videoId)
    }
}