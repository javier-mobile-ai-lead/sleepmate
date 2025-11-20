package com.sleepmate.domain.di;

@dagger.Module()
@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c7\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\nH\u0007J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0006\u001a\u00020\nH\u0007J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00020\nH\u0007J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0006\u001a\u00020\nH\u0007J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0006\u001a\u00020\nH\u0007J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0006\u001a\u00020\nH\u0007J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0006\u001a\u00020\nH\u0007J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0007\u00a8\u0006\u001b"}, d2 = {"Lcom/sleepmate/domain/di/DomainModule;", "", "<init>", "()V", "provideGetSleepHabitsUseCase", "Lcom/sleepmate/domain/usecase/GetSleepHabitsUseCase;", "repository", "Lcom/sleepmate/domain/repository/SleepHabitRepository;", "provideGetVideosRecommendationsUseCase", "Lcom/sleepmate/domain/usecase/GetVideoRecommendationsUseCase;", "Lcom/sleepmate/domain/repository/VideoRepository;", "provideGetVideosFavoritesUseCase", "Lcom/sleepmate/domain/usecase/GetVideoRecommendationRefreshUseCase;", "provideGetFavoritesIdsUseCase", "Lcom/sleepmate/domain/usecase/GetVideoFavoritesIdsUseCase;", "provideAddToFavoritesUseCase", "Lcom/sleepmate/domain/usecase/AddVideoFavoritesUseCase;", "provideRemoveFromFavoritesUseCase", "Lcom/sleepmate/domain/usecase/RemoveFavoritesUseCase;", "provideToggleFavoriteUseCase", "Lcom/sleepmate/domain/usecase/ToggleFavoriteUseCase;", "provideIsFavoriteUseCase", "Lcom/sleepmate/domain/usecase/IsFavoriteUseCase;", "provideUserProgressTracker", "Lcom/sleepmate/domain/usecase/UserProgressTracker;", "trackerDataSource", "Lcom/sleepmate/domain/datasource/TrackerDataSource;", "domain_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public final class DomainModule {
    @org.jetbrains.annotations.NotNull()
    public static final com.sleepmate.domain.di.DomainModule INSTANCE = null;
    
    private DomainModule() {
        super();
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.sleepmate.domain.usecase.GetSleepHabitsUseCase provideGetSleepHabitsUseCase(@org.jetbrains.annotations.NotNull()
    com.sleepmate.domain.repository.SleepHabitRepository repository) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.sleepmate.domain.usecase.GetVideoRecommendationsUseCase provideGetVideosRecommendationsUseCase(@org.jetbrains.annotations.NotNull()
    com.sleepmate.domain.repository.VideoRepository repository) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.sleepmate.domain.usecase.GetVideoRecommendationRefreshUseCase provideGetVideosFavoritesUseCase(@org.jetbrains.annotations.NotNull()
    com.sleepmate.domain.repository.VideoRepository repository) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.sleepmate.domain.usecase.GetVideoFavoritesIdsUseCase provideGetFavoritesIdsUseCase(@org.jetbrains.annotations.NotNull()
    com.sleepmate.domain.repository.VideoRepository repository) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.sleepmate.domain.usecase.AddVideoFavoritesUseCase provideAddToFavoritesUseCase(@org.jetbrains.annotations.NotNull()
    com.sleepmate.domain.repository.VideoRepository repository) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.sleepmate.domain.usecase.RemoveFavoritesUseCase provideRemoveFromFavoritesUseCase(@org.jetbrains.annotations.NotNull()
    com.sleepmate.domain.repository.VideoRepository repository) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.sleepmate.domain.usecase.ToggleFavoriteUseCase provideToggleFavoriteUseCase(@org.jetbrains.annotations.NotNull()
    com.sleepmate.domain.repository.VideoRepository repository) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.sleepmate.domain.usecase.IsFavoriteUseCase provideIsFavoriteUseCase(@org.jetbrains.annotations.NotNull()
    com.sleepmate.domain.repository.VideoRepository repository) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.sleepmate.domain.usecase.UserProgressTracker provideUserProgressTracker(@org.jetbrains.annotations.NotNull()
    com.sleepmate.domain.datasource.TrackerDataSource trackerDataSource) {
        return null;
    }
}