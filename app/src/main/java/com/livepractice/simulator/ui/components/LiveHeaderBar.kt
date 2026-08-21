package com.livepractice.simulator.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.livepractice.simulator.ui.theme.*
import java.util.Locale

@Composable
fun LiveHeaderBar(
    scenarioTitle: String,
    viewerCount: Int,
    durationSeconds: Long,
    isCameraEnabled: Boolean,
    isMuted: Boolean,
    onToggleCamera: () -> Unit,
    onSwitchCamera: () -> Unit,
    onToggleMute: () -> Unit,
    onCycleBackground: () -> Unit,
    onEndLive: () -> Unit,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "livePulse")
    val dotAlpha by infiniteTransition.animateFloat(
        initialValue = 0.4f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(600, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "dotAlpha"
    )

    val minutes = durationSeconds / 60
    val seconds = durationSeconds % 60
    val formattedDuration = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.Black.copy(alpha = 0.5f))
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(LiveRed.copy(alpha = dotAlpha))
                )
                Text(
                    text = "LIVE",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp
                )
            }

            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.Black.copy(alpha = 0.5f))
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    imageVector = AppIcons.Visibility,
                    contentDescription = "Viewers",
                    tint = TextPrimary,
                    modifier = Modifier.size(13.dp)
                )
                Text(
                    text = formatViewerCount(viewerCount),
                    color = TextPrimary,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 11.sp
                )
            }

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.Black.copy(alpha = 0.5f))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = formattedDuration,
                    color = TextSecondary,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            IconButton(
                onClick = {
                    if (isCameraEnabled) onSwitchCamera() else onCycleBackground()
                },
                modifier = Modifier
                    .size(34.dp)
                    .clip(CircleShape)
                    .background(Color.Black.copy(alpha = 0.5f))
                    .testTag("switch_camera_button")
            ) {
                Icon(
                    imageVector = if (isCameraEnabled) AppIcons.Cameraswitch else AppIcons.Palette,
                    contentDescription = "Switch Camera / BG",
                    tint = TextPrimary,
                    modifier = Modifier.size(18.dp)
                )
            }

            IconButton(
                onClick = onToggleCamera,
                modifier = Modifier
                    .size(34.dp)
                    .clip(CircleShape)
                    .background(Color.Black.copy(alpha = 0.5f))
                    .testTag("toggle_camera_button")
            ) {
                Icon(
                    imageVector = if (isCameraEnabled) AppIcons.Videocam else AppIcons.VideocamOff,
                    contentDescription = "Toggle Camera",
                    tint = if (isCameraEnabled) TextPrimary else LiveRed,
                    modifier = Modifier.size(18.dp)
                )
            }

            IconButton(
                onClick = onToggleMute,
                modifier = Modifier
                    .size(34.dp)
                    .clip(CircleShape)
                    .background(Color.Black.copy(alpha = 0.5f))
                    .testTag("toggle_mic_button")
            ) {
                Icon(
                    imageVector = if (isMuted) AppIcons.MicOff else AppIcons.Mic,
                    contentDescription = "Toggle Mic",
                    tint = if (isMuted) LiveRed else TextPrimary,
                    modifier = Modifier.size(18.dp)
                )
            }

            IconButton(
                onClick = onEndLive,
                modifier = Modifier
                    .size(34.dp)
                    .clip(CircleShape)
                    .background(LiveRed)
                    .testTag("end_stream_button")
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "End Live",
                    tint = Color.White,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}

private fun formatViewerCount(count: Int): String {
    return when {
        count >= 1_000_000 -> String.format(Locale.getDefault(), "%.1fM", count / 1_000_000.0)
        count >= 1_000 -> String.format(Locale.getDefault(), "%.1fK", count / 1_000.0)
        else -> count.toString()
    }
}
