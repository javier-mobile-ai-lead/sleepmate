package com.sleepmate.app.ui.screen.videorecommendations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sleepmate.app.util.YouTubeUtils
import com.sleepmate.domain.model.VideoItem
import com.sleepmate.domain.usecase.AddVideoFavoritesUseCase
import com.sleepmate.domain.usecase.GetVideoFavoritesIdsUseCase
import com.sleepmate.domain.usecase.GetVideoRecommendationRefreshUseCase
import com.sleepmate.domain.usecase.GetVideoRecommendationsUseCase
import com.sleepmate.domain.usecase.RemoveFavoritesUseCase
import com.sleepmate.domain.usecase.ToggleFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

data class VideoRecommendationsUiState(
    val videos: List<VideoItem> = emptyList(),
    val favoriteVideoIds: Set<String> = emptySet(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val hasConnectionError: Boolean = false
)

@HiltViewModel
class VideoRecommendationsViewModel @Inject constructor(
    val useCaseGetRecommendations: GetVideoRecommendationsUseCase,
    val useCaseRefreshRecommendations: GetVideoRecommendationRefreshUseCase,
    val useCaseFavoritesIdsUseCase: GetVideoFavoritesIdsUseCase,
    val useCaseToggleFavorite: ToggleFavoriteUseCase,
    val addVideoFavoritesUseCase: AddVideoFavoritesUseCase,
    val removeFavoritesUseCase: RemoveFavoritesUseCase
    ) : ViewModel() {
    
    private val _uiState = MutableStateFlow(VideoRecommendationsUiState())
    val uiState: StateFlow<VideoRecommendationsUiState> = _uiState.asStateFlow()
    
    init {
        loadVideos()
        observeFavorites()
    }
    
    private fun loadVideos() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null, hasConnectionError = false)
            
            try {
                useCaseGetRecommendations()
                    .catch { exception ->
                        Timber.e(exception, "Error loading videos")
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            hasConnectionError = true,
                            error = "No pudimos cargar los videos. Verifica tu conexión."
                        )
                    }
                    .collect { videos ->
                        // Filter only videos with valid YouTube URLs
                        val validVideos : MutableList<VideoItem> = videos.filter { video ->
                            YouTubeUtils.isValidYouTubeUrl(video.videoUrl)
                        }.toMutableList()
                        val orderByFavorite = videos.sortedBy { video ->
                        // Ordena por prioridad: los favoritos primero (false < true)
                        !_uiState.value.favoriteVideoIds.contains(video.id)
                    }
                        _uiState.value = _uiState.value.copy(
                            videos = orderByFavorite,
                            isLoading = false,
                            error = null,
                            hasConnectionError = false
                        )
                        
                        Timber.d("Loaded ${validVideos.size} valid videos out of ${videos.size} total")
                    }
            } catch (e: Exception) {
                Timber.e(e, "Error loading videos")
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    hasConnectionError = true,
                    error = "No pudimos cargar los videos. Verifica tu conexión."
                )
            }
        }
    }
    
    private fun observeFavorites() {
        viewModelScope.launch {
            try {
                useCaseFavoritesIdsUseCase()
                    .collect { favoriteIds ->
                        _uiState.value = _uiState.value.copy(favoriteVideoIds = favoriteIds)
                        Timber.d("Updated favorites: ${favoriteIds.size} videos")
                    }
            } catch (e: Exception) {
                Timber.e(e, "Error observing favorites")
            }
        }
    }
    
    fun toggleFavorite(videoId: String) {
        viewModelScope.launch {
            try {

                val updated = if (_uiState.value.favoriteVideoIds.contains(videoId)) {
                    removeFavoritesUseCase(videoId)
                    _uiState.value.favoriteVideoIds - videoId
                } else {
                    addVideoFavoritesUseCase(videoId)
                    _uiState.value.favoriteVideoIds + videoId
                }
                _uiState.update {
                    it.copy(favoriteVideoIds = updated)
                }
            } catch (e: Exception) {
                Timber.e(e, "Error toggling favorite")
            }
        }
    }
    
    fun retryLoadingVideos() {
        loadVideos()
    }
    
    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null, hasConnectionError = false)
    }
    
    fun refreshVideos() {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true, error = null)
                useCaseRefreshRecommendations()
                Timber.d("Refreshed videos successfully")
            } catch (e: Exception) {
                Timber.e(e, "Error refreshing videos")
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = "Error al actualizar los videos"
                )
            }
        }
    }
    
    fun isVideoFavorite(videoItem: VideoItem): Boolean {
        val videoId = YouTubeUtils.extractVideoId(videoItem.videoUrl)
        return videoId?.let { 
            !_uiState.value.favoriteVideoIds.contains(it)
        } ?: false
    }
}