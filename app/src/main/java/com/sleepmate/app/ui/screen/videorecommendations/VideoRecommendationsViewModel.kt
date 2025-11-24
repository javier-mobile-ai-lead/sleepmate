package com.sleepmate.app.ui.screen.videorecommendations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sleepmate.app.util.YouTubeUtils
import com.sleepmate.domain.model.VideoItem
import com.sleepmate.domain.usecase.GetVideoFavoritesIdsUseCase
import com.sleepmate.domain.usecase.ToggleFavoriteUseCase
import com.sleepmate.domain.usecase.GetVideoRecommendationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideoRecommendationsViewModel @Inject constructor(
    private val getVideoRecommendationsUseCase: GetVideoRecommendationsUseCase,
    private val getVideoFavoritesIdsUseCase: GetVideoFavoritesIdsUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(VideoRecommendationsUiState())
    val uiState: StateFlow<VideoRecommendationsUiState> = _uiState.asStateFlow()

    private val refreshTrigger = MutableSharedFlow<Unit>(replay = 1)

    init {
        viewModelScope.launch {
            refreshTrigger
                .onStart { emit(Unit) }
                .flatMapLatest {
                    _uiState.update { it.copy(isLoading = true, error = null) }
                    getVideoRecommendationsUseCase()
                }
                .combine(getVideoFavoritesIdsUseCase()) { allVideos, favoriteIds ->
                    val validVideos = allVideos.filter { YouTubeUtils.extractVideoId(it.videoUrl) != null }
                    val sortedVideos = validVideos.sortedByDescending { favoriteIds.contains(it.id) }
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            videos = sortedVideos,
                            favoriteVideoIds = favoriteIds,
                            error = null
                        )
                    }
                }
                .catch { e ->
                    _uiState.update {
                        it.copy(isLoading = false, error = e.message ?: "An unknown error occurred")
                    }
                }
                .collect()
        }
    }

    fun toggleFavorite(videoId: String) {
        viewModelScope.launch {
            toggleFavoriteUseCase(videoId)
        }
    }

    fun refreshVideos() {
        viewModelScope.launch {
            refreshTrigger.emit(Unit)
        }
    }

    fun retryLoadingVideos() {
        viewModelScope.launch {
            refreshTrigger.emit(Unit)
        }
    }

    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }
}

data class VideoRecommendationsUiState(
    val isLoading: Boolean = true,
    val videos: List<VideoItem> = emptyList(),
    val favoriteVideoIds: Set<String> = emptySet(),
    val error: String? = null
)
