package com.sleepmate.domain.usecase

import com.sleepmate.domain.repository.VideoRepository
import javax.inject.Inject

class GetVideoRecommendationRefreshUseCase @Inject constructor(
    private val repository: VideoRepository
) {
    suspend operator fun invoke() {
        return repository.refreshVideoRecommendations()
    }
}