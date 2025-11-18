package com.sleepmate.domain.usecase

import com.sleepmate.domain.repository.VideoRepository
import javax.inject.Inject

class IsFavoriteUseCase @Inject constructor(
    private val repository: VideoRepository
) {
    suspend operator fun invoke(videoId: String) : Boolean {
        return repository.isFavorite(videoId = videoId)
    }
}