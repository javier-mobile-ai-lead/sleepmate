package com.sleepmate.data.remote.api;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J*\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0003\u0010\u0007\u001a\u00020\bH\u00a7@\u00a2\u0006\u0002\u0010\tJ(\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u000b\u001a\u00020\u00062\b\b\u0003\u0010\u0007\u001a\u00020\bH\u00a7@\u00a2\u0006\u0002\u0010\t\u00a8\u0006\f\u00c0\u0006\u0003"}, d2 = {"Lcom/sleepmate/data/remote/api/VideoApi;", "", "getVideoRecommendations", "", "Lcom/sleepmate/data/remote/dto/VideoRecommendationDto;", "category", "", "limit", "", "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "searchVideos", "query", "data_debug"})
public abstract interface VideoApi {
    
    @retrofit2.http.GET(value = "videos/recommendations")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getVideoRecommendations(@retrofit2.http.Query(value = "category")
    @org.jetbrains.annotations.Nullable()
    java.lang.String category, @retrofit2.http.Query(value = "limit")
    int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.sleepmate.data.remote.dto.VideoRecommendationDto>> $completion);
    
    @retrofit2.http.GET(value = "videos/search")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object searchVideos(@retrofit2.http.Query(value = "query")
    @org.jetbrains.annotations.NotNull()
    java.lang.String query, @retrofit2.http.Query(value = "limit")
    int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.sleepmate.data.remote.dto.VideoRecommendationDto>> $completion);
    
    @kotlin.Metadata(mv = {2, 2, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}