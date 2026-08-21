package com.livepractice.simulator.data.model

import androidx.compose.ui.graphics.Color

data class LiveComment(
    val id: String,
    val username: String,
    val text: String,
    val timestamp: Long = System.currentTimeMillis(),
    val avatarColorHex: Long = 0xFF6200EE,
    val isQuestion: Boolean = false,
    val isSuperChat: Boolean = false,
    val superAmount: String? = null,
    val giftEmoji: String? = null,
    val isPinned: Boolean = false,
    val verified: Boolean = false
)

data class LiveScenario(
    val id: String,
    val title: String,
    val description: String,
    val category: String,
    val icon: String,
    val baseViewers: Int,
    val commentPaceMs: Long,
    val sampleComments: List<String>,
    val promptChallenges: List<String>,
    val difficulty: String
)

data class GiftAnimationEvent(
    val id: String,
    val username: String,
    val giftName: String,
    val giftEmoji: String,
    val coinValue: Int
)

data class HeartParticle(
    val id: Long,
    val startXFraction: Float,
    val color: Color,
    val emoji: String = "❤️"
)
