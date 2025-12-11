package com.sleepmate.domain.model

data class Alarm(
    val id: Int = 1,
    val time: String = "07:00",
    val name: String = "Despertar",
    val days: List<String> = emptyList(),
    val sound: String = "Mañana",
    val isEnabled: Boolean = false
)
