package com.sleepmate.data.repository;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0014\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\tH\u0016J\u000e\u0010\f\u001a\u00020\rH\u0096@\u00a2\u0006\u0002\u0010\u000eJ\u0014\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\tH\u0016J\u0016\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u0011H\u0096@\u00a2\u0006\u0002\u0010\u0014J\u0016\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u0011H\u0096@\u00a2\u0006\u0002\u0010\u0014J\u0016\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u0011H\u0096@\u00a2\u0006\u0002\u0010\u0014J\u0016\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0013\u001a\u00020\u0011H\u0096@\u00a2\u0006\u0002\u0010\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"Lcom/sleepmate/data/repository/VideoRepositoryImpl;", "Lcom/sleepmate/domain/repository/VideoRepository;", "remoteDataSource", "Lcom/sleepmate/data/datasource/remote/VideoRemoteDataSource;", "favoritesDataStore", "Lcom/sleepmate/data/datasource/local/VideoFavoritesDataStore;", "<init>", "(Lcom/sleepmate/data/datasource/remote/VideoRemoteDataSource;Lcom/sleepmate/data/datasource/local/VideoFavoritesDataStore;)V", "getVideoRecommendations", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/sleepmate/domain/model/VideoItem;", "refreshVideoRecommendations", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getFavoriteVideoIds", "", "", "addToFavorites", "videoId", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "removeFromFavorites", "toggleFavorite", "isFavorite", "", "data_debug"})
public final class VideoRepositoryImpl implements com.sleepmate.domain.repository.VideoRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.sleepmate.data.datasource.remote.VideoRemoteDataSource remoteDataSource = null;
    @org.jetbrains.annotations.NotNull()
    private final com.sleepmate.data.datasource.local.VideoFavoritesDataStore favoritesDataStore = null;
    
    @javax.inject.Inject()
    public VideoRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.sleepmate.data.datasource.remote.VideoRemoteDataSource remoteDataSource, @org.jetbrains.annotations.NotNull()
    com.sleepmate.data.datasource.local.VideoFavoritesDataStore favoritesDataStore) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.sleepmate.domain.model.VideoItem>> getVideoRecommendations() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object refreshVideoRecommendations(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.Set<java.lang.String>> getFavoriteVideoIds() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object addToFavorites(@org.jetbrains.annotations.NotNull()
    java.lang.String videoId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object removeFromFavorites(@org.jetbrains.annotations.NotNull()
    java.lang.String videoId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object toggleFavorite(@org.jetbrains.annotations.NotNull()
    java.lang.String videoId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object isFavorite(@org.jetbrains.annotations.NotNull()
    java.lang.String videoId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
}