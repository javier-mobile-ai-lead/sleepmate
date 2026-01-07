package com.sleepmate.domain.model

data class UserProfile(
    val name: String = "",
    val ageRange: String = "",
    val stressLevel: Int = 3,
    val stimulantConsumption: String = "",
    val preferences: List<String> = emptyList(),
    val goals: List<String> = emptyList()
)
