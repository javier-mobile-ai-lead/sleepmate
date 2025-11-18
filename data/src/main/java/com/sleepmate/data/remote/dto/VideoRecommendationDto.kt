package com.sleepmate.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.sleepmate.domain.model.VideoRecommendation

data class VideoRecommendationDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("thumbnail_url")
    val thumbnailUrl: String,
    @SerializedName("video_url")
    val videoUrl: String,
    @SerializedName("duration")
    val duration: Int,
    @SerializedName("category")
    val category: String,
    @SerializedName("rating")
    val rating: Float
)

fun VideoRecommendationDto.toDomain(): VideoRecommendation {
    return VideoRecommendation(
        id = id,
        title = title,
        description = description,
        thumbnailUrl = thumbnailUrl,
        videoUrl = videoUrl,
        duration = duration,
        category = category,
        rating = rating
    )
}