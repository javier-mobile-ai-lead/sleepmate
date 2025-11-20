package com.sleepmate.data.datasource.remote;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0011\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0007J\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0086@\u00a2\u0006\u0002\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/sleepmate/data/datasource/remote/VideoRemoteDataSource;", "", "firestore", "Lcom/google/firebase/firestore/FirebaseFirestore;", "<init>", "(Lcom/google/firebase/firestore/FirebaseFirestore;)V", "getVideoRecommendations", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/sleepmate/domain/model/VideoItem;", "refreshVideoRecommendations", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "data_debug"})
public final class VideoRemoteDataSource {
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.firestore.FirebaseFirestore firestore = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String COLLECTION_NAME = "video_recommendations";
    @org.jetbrains.annotations.NotNull()
    public static final com.sleepmate.data.datasource.remote.VideoRemoteDataSource.Companion Companion = null;
    
    @javax.inject.Inject()
    public VideoRemoteDataSource(@org.jetbrains.annotations.NotNull()
    com.google.firebase.firestore.FirebaseFirestore firestore) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.sleepmate.domain.model.VideoItem>> getVideoRecommendations() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object refreshVideoRecommendations(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.sleepmate.domain.model.VideoItem>> $completion) {
        return null;
    }
    
    @kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/sleepmate/data/datasource/remote/VideoRemoteDataSource$Companion;", "", "<init>", "()V", "COLLECTION_NAME", "", "data_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}