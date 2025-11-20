package com.sleepmate.domain.usecase

import com.sleepmate.domain.repository.VideoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetVideoFavoritesIdsUseCase @Inject constructor(
    private val repository: VideoRepository
) {
    operator fun invoke(): Flow<Set<String>> {
        return repository.getFavoriteVideoIds()
    }
}