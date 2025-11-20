package com.sleepmate.data.datasource.local;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0007\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0011\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0007J\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\tH\u0086@\u00a2\u0006\u0002\u0010\rJ\u0016\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\tH\u0086@\u00a2\u0006\u0002\u0010\rJ\u0016\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\tH\u0086@\u00a2\u0006\u0002\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/sleepmate/data/datasource/local/VideoFavoritesDataStore;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "getFavoriteVideoIds", "Lkotlinx/coroutines/flow/Flow;", "", "", "addToFavorites", "", "videoId", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "removeFromFavorites", "toggleFavorite", "Companion", "data_debug"})
public final class VideoFavoritesDataStore {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.util.Set<java.lang.String>> FAVORITE_VIDEO_IDS_KEY = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.sleepmate.data.datasource.local.VideoFavoritesDataStore.Companion Companion = null;
    
    @javax.inject.Inject()
    public VideoFavoritesDataStore(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.Set<java.lang.String>> getFavoriteVideoIds() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object addToFavorites(@org.jetbrains.annotations.NotNull()
    java.lang.String videoId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object removeFromFavorites(@org.jetbrains.annotations.NotNull()
    java.lang.String videoId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object toggleFavorite(@org.jetbrains.annotations.NotNull()
    java.lang.String videoId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u001a\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/sleepmate/data/datasource/local/VideoFavoritesDataStore$Companion;", "", "<init>", "()V", "FAVORITE_VIDEO_IDS_KEY", "Landroidx/datastore/preferences/core/Preferences$Key;", "", "", "data_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}