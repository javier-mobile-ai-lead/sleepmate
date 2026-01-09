package com.sleepmate.domain.model

data class UserProfile(
    val name: String = "",
    val age: Int? = null,
    val ageRange: String = "", // Keeping for backward compatibility or transition if needed, but will prioritize 'age'
    val weight: Double? = null,
    val height: Double? = null,
    val stressLevel: Int = 3,
    val caffeineConsumption: String = "", // More specific than general stimulants
    val stimulantConsumption: String = "",
    val preferences: List<String> = emptyList(),
    val goals: List<String> = emptyList()
)
