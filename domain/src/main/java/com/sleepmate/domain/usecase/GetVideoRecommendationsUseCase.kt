package com.sleepmate.domain.usecase

import com.sleepmate.domain.model.VideoItem
import com.sleepmate.domain.model.VideoRecommendation
import com.sleepmate.domain.repository.VideoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetVideoRecommendationsUseCase @Inject constructor(
    private val repository: VideoRepository
) {
    suspend operator fun invoke(): Flow<List<VideoItem>> {
        return repository.getVideoRecommendations()
    }
}