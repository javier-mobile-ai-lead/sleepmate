package com.sleepmate.domain.usecase

import com.sleepmate.domain.repository.VideoRepository
import javax.inject.Inject

class AddVideoFavoritesUseCase @Inject constructor(
    private val repository: VideoRepository
) {
    suspend operator fun invoke(videoId: String) {
        return repository.addToFavorites(videoId)
    }
}