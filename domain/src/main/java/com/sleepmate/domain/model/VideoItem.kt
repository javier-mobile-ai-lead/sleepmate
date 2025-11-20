package com.sleepmate.domain.model

data class VideoItem(
    val id: String,
    val title: String,
    val description: String,
    val thumbnailUrl: String,
    val videoUrl: String,
    val duration: Int, // in seconds
    val category: String,
    val rating: Float = 0f
)