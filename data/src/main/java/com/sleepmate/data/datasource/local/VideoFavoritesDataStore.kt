package com.sleepmate.data.datasource.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.favoritesDataStore: DataStore<Preferences> by preferencesDataStore(name = "video_favorites_preferences")

@Singleton
class VideoFavoritesDataStore @Inject constructor(
    private val context: Context
) {
    companion object {
        private val FAVORITE_VIDEO_IDS_KEY = stringSetPreferencesKey("favorite_video_ids")
    }
    
    fun getFavoriteVideoIds(): Flow<Set<String>> {
        return context.favoritesDataStore.data.map { preferences ->
            preferences[FAVORITE_VIDEO_IDS_KEY] ?: emptySet()
        }
    }
    
    suspend fun addToFavorites(videoId: String) {
        context.favoritesDataStore.edit { preferences ->
            val currentFavorites = preferences[FAVORITE_VIDEO_IDS_KEY] ?: emptySet()
            preferences[FAVORITE_VIDEO_IDS_KEY] = currentFavorites + videoId
        }
    }
    
    suspend fun removeFromFavorites(videoId: String) {
        context.favoritesDataStore.edit { preferences ->
            val currentFavorites = preferences[FAVORITE_VIDEO_IDS_KEY] ?: emptySet()
            preferences[FAVORITE_VIDEO_IDS_KEY] = currentFavorites - videoId
        }
    }
    
    suspend fun toggleFavorite(videoId: String) {
        context.favoritesDataStore.edit { preferences ->
            val currentFavorites = preferences[FAVORITE_VIDEO_IDS_KEY] ?: emptySet()
            preferences[FAVORITE_VIDEO_IDS_KEY] = if (videoId in currentFavorites) {
                currentFavorites - videoId
            } else {
                currentFavorites + videoId
            }
        }
    }
}