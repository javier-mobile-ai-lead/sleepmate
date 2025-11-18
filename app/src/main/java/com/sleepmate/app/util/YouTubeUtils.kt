package com.sleepmate.app.util

import java.util.regex.Pattern

object YouTubeUtils {
    
    private val YOUTUBE_URL_PATTERNS = listOf(
        Pattern.compile("(?:youtube\\.com/watch\\?v=|youtu\\.be/|youtube\\.com/embed/)([a-zA-Z0-9_-]{11})"),
        Pattern.compile("youtube\\.com.*[?&]v=([a-zA-Z0-9_-]{11})"),
        Pattern.compile("youtu\\.be/([a-zA-Z0-9_-]{11})")
    )
    
    /**
     * Extrae el videoId de una URL de YouTube
     * @param url URL de YouTube (ej: https://www.youtube.com/watch?v=dQw4w9WgXcQ)
     * @return videoId si es válido, null si no es válido
     */
    fun extractVideoId(url: String?): String? {
        if (url.isNullOrBlank()) return null
        
        return YOUTUBE_URL_PATTERNS.firstNotNullOfOrNull { pattern ->
            val matcher = pattern.matcher(url)
            if (matcher.find()) matcher.group(1) else null
        }
    }
    
    /**
     * Valida si una URL de YouTube contiene un videoId válido
     */
    fun isValidYouTubeUrl(url: String?): Boolean {
        return extractVideoId(url) != null
    }
    
    /**
     * Genera una URL de thumbnail para un video de YouTube
     */
    fun getThumbnailUrl(videoId: String): String {
        return "https://img.youtube.com/vi/$videoId/maxresdefault.jpg"
    }
    
    /**
     * Genera una URL estándar de YouTube a partir de un videoId
     */
    fun getYouTubeUrl(videoId: String): String {
        return "https://www.youtube.com/watch?v=$videoId"
    }
}