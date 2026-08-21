package com.livepractice.simulator.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "session_logs")
data class SessionLog(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val scenarioName: String,
    val dateTimestamp: Long = System.currentTimeMillis(),
    val durationSeconds: Long,
    val peakViewers: Int,
    val totalLikes: Int,
    val totalComments: Int,
    val giftsReceived: Int,
    val performanceRating: Int = 5,
    val userNotes: String = ""
)
