package com.sleepmate.domain.di

import com.sleepmate.domain.datasource.TrackerDataSource
import com.sleepmate.domain.repository.SleepHabitRepository
import com.sleepmate.domain.repository.VideoRepository
import com.sleepmate.domain.usecase.AddVideoFavoritesUseCase
import com.sleepmate.domain.usecase.GetSleepHabitsUseCase
import com.sleepmate.domain.usecase.GetVideoFavoritesIdsUseCase
import com.sleepmate.domain.usecase.GetVideoRecommendationRefreshUseCase
import com.sleepmate.domain.usecase.GetVideoRecommendationsUseCase
import com.sleepmate.domain.usecase.IsFavoriteUseCase
import com.sleepmate.domain.usecase.RemoveFavoritesUseCase
import com.sleepmate.domain.usecase.ToggleFavoriteUseCase
import com.sleepmate.domain.usecase.UserProgressTracker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    
    @Provides
    @Singleton
    fun provideGetSleepHabitsUseCase(
        repository: SleepHabitRepository
    ): GetSleepHabitsUseCase {
        return GetSleepHabitsUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetVideosRecommendationsUseCase(
        repository: VideoRepository
    ): GetVideoRecommendationsUseCase {
        return GetVideoRecommendationsUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetVideosFavoritesUseCase(
        repository: VideoRepository
    ): GetVideoRecommendationRefreshUseCase {
        return GetVideoRecommendationRefreshUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetFavoritesIdsUseCase(
        repository: VideoRepository
    ): GetVideoFavoritesIdsUseCase{
        return GetVideoFavoritesIdsUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideAddToFavoritesUseCase(
        repository: VideoRepository
    ): AddVideoFavoritesUseCase {
        return AddVideoFavoritesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideRemoveFromFavoritesUseCase(
        repository: VideoRepository
    ): RemoveFavoritesUseCase {
        return RemoveFavoritesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideToggleFavoriteUseCase(
        repository: VideoRepository
    ): ToggleFavoriteUseCase{
        return ToggleFavoriteUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideIsFavoriteUseCase(
        repository: VideoRepository
    ): IsFavoriteUseCase {
        return IsFavoriteUseCase(repository)
    }
    
    @Provides
    @Singleton
    fun provideUserProgressTracker(
        trackerDataSource: TrackerDataSource
    ): UserProgressTracker {
        return UserProgressTracker(trackerDataSource)
    }
}