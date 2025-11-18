package com.sleepmate.data.model

import com.google.gson.annotations.SerializedName

data class GPTRequest(
    @SerializedName("model")
    val model: String = "o3-mini",
    @SerializedName("messages")
    val messages: List<GPTMessage>,
    @SerializedName("max_tokens")
    val maxTokens: Int = 500,
    @SerializedName("temperature")
    val temperature: Double = 0.7
)

data class GPTMessage(
    @SerializedName("role")
    val role: String, // "system", "user", "assistant"
    @SerializedName("content")
    val content: String
)

data class GPTResponse(
    @SerializedName("id")
    val id: String?,
    @SerializedName("choices")
    val choices: List<GPTChoice>?,
    @SerializedName("error")
    val error: GPTError?
)

data class GPTChoice(
    @SerializedName("message")
    val message: GPTMessage?,
    @SerializedName("finish_reason")
    val finishReason: String?
)

data class GPTError(
    @SerializedName("message")
    val message: String?,
    @SerializedName("type")
    val type: String?
)