package com.sleepmate.data.datasource.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.sleepmate.domain.model.VideoItem
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VideoRemoteDataSource @Inject constructor(
    private val firestore: FirebaseFirestore
) {
    companion object {
        private const val COLLECTION_NAME = "video_recommendations"
    }
    
    fun getVideoRecommendations(): Flow<List<VideoItem>> = callbackFlow {
        val listener = firestore.collection(COLLECTION_NAME)
            .addSnapshotListener { snapshot, exception ->
                if (exception != null) {
                    Timber.e(exception, "Error listening to video recommendations")
                    trySend(emptyList())
                    return@addSnapshotListener
                }
                
                try {
                    val videos = snapshot?.documents?.mapNotNull { document ->
                        try {
                            VideoItem(
                                id = document.id,
                                title = document.getString("title") ?: "",
                                description = document.getString("description") ?: "",
                                thumbnailUrl = document.getString("thumbnail_url") ?: "",
                                videoUrl = document.getString("video_url") ?: "",
                                duration = 3600,
                                category = document.getString("category") ?: "",
                                rating = document.getDouble("rating")?.toFloat() ?: 1f,
                            )
                        } catch (e: Exception) {
                            Timber.e(e, "Error parsing video document: ${document.id}")
                            null
                        }
                    } ?: emptyList()
                    
                    trySend(videos)
                    Timber.d("Loaded ${videos.size} video recommendations")
                } catch (e: Exception) {
                    Timber.e(e, "Error processing video recommendations")
                    trySend(emptyList())
                }
            }
        
        awaitClose { listener.remove() }
    }
    
    suspend fun refreshVideoRecommendations(): List<VideoItem> {
        return try {
            val snapshot = firestore.collection(COLLECTION_NAME)
                .get()
                .await()
            
            snapshot.documents.mapNotNull { document ->
                try {
                    VideoItem(
                        id = document.id,
                        title = document.getString("title") ?: "",
                        description = document.getString("description") ?: "",
                        thumbnailUrl = document.getString("thumbnail_url") ?: "",
                        videoUrl = document.getString("video_url") ?: "",
                        duration = 3600,
                        category = document.getString("category") ?: "",
                        rating = document.getDouble("rating")?.toFloat() ?: 1f,
                    )
                } catch (e: Exception) {
                    Timber.e(e, "Error parsing video document: ${document.id}")
                    null
                }
            }
        } catch (e: Exception) {
            Timber.e(e, "Error refreshing video recommendations")
            emptyList()
        }
    }

}