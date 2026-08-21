package com.livepractice.simulator.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.livepractice.simulator.ui.theme.*
import com.livepractice.simulator.ui.viewmodel.SimulationViewModel
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SessionSummaryScreen(
    viewModel: SimulationViewModel,
    onDone: () -> Unit,
    onPracticeAgain: () -> Unit
) {
    val session by viewModel.lastCompletedSession.collectAsStateWithLifecycle()
    var rating by remember(session) { mutableStateOf(session?.performanceRating ?: 5) }
    var notes by remember(session) { mutableStateOf(session?.userNotes ?: "") }

    val durationMin = (session?.durationSeconds ?: 0L) / 60
    val durationSec = (session?.durationSeconds ?: 0L) % 60
    val engagementScore = calculateEngagementScore(
        durationSeconds = session?.durationSeconds ?: 0L,
        likes = session?.totalLikes ?: 0,
        comments = session?.totalComments ?: 0,
        peakViewers = session?.peakViewers ?: 0
    )

    Scaffold(
        containerColor = DarkBackground,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Session Debrief & Analytics",
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary,
                        fontSize = 18.sp
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = DarkSurface
                )
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            item {
                Card(
                    colors = CardDefaults.cardColors(containerColor = DarkSurface),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, DarkSurfaceHighlight, RoundedCornerShape(16.dp))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .size(64.dp)
                                .clip(CircleShape)
                                .background(
                                    Brush.linearGradient(listOf(NeonPink, PurpleAccent))
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "🎉", fontSize = 28.sp)
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = "Stream Completed!",
                            color = TextPrimary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )

                        Text(
                            text = session?.scenarioName ?: "Live Stream Practice",
                            color = ElectricCyan,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            modifier = Modifier
                                .clip(RoundedCornerShape(20.dp))
                                .background(DarkSurfaceElevated)
                                .padding(horizontal = 16.dp, vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Icon(
                                imageVector = AppIcons.TrendingUp,
                                contentDescription = null,
                                tint = BrightYellow,
                                modifier = Modifier.size(18.dp)
                            )
                            Text(
                                text = "Engagement Rating: $engagementScore%",
                                color = BrightYellow,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }

            item {
                Text(
                    text = "Key Metrics",
                    color = TextPrimary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    MetricCard(
                        title = "Peak Audience",
                        value = "${session?.peakViewers ?: 0}",
                        icon = AppIcons.Visibility,
                        accentColor = ElectricCyan,
                        modifier = Modifier.weight(1f)
                    )
                    MetricCard(
                        title = "Practice Time",
                        value = String.format(Locale.getDefault(), "%02d:%02d", durationMin, durationSec),
                        icon = AppIcons.Timer,
                        accentColor = NeonPink,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    MetricCard(
                        title = "Simulated Likes",
                        value = "${session?.totalLikes ?: 0}",
                        icon = Icons.Default.Favorite,
                        accentColor = LiveRed,
                        modifier = Modifier.weight(1f)
                    )
                    MetricCard(
                        title = "Chat Comments",
                        value = "${session?.totalComments ?: 0}",
                        icon = AppIcons.ChatBubble,
                        accentColor = BrightYellow,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            item {
                Card(
                    colors = CardDefaults.cardColors(containerColor = DarkSurfaceElevated),
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "Self-Evaluation Rating",
                            color = TextPrimary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            (1..5).forEach { starIndex ->
                                IconButton(
                                    onClick = {
                                        rating = starIndex
                                        session?.let {
                                            viewModel.updateSessionNotes(it, notes, rating)
                                        }
                                    },
                                    modifier = Modifier.size(36.dp)
                                ) {
                                    Icon(
                                        imageVector = if (starIndex <= rating) Icons.Default.Star else AppIcons.StarBorder,
                                        contentDescription = "Star $starIndex",
                                        tint = if (starIndex <= rating) GoldenBadge else TextMuted,
                                        modifier = Modifier.size(28.dp)
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        Text(
                            text = "Speech & Delivery Notes",
                            color = TextPrimary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        OutlinedTextField(
                            value = notes,
                            onValueChange = {
                                notes = it
                                session?.let { s ->
                                    viewModel.updateSessionNotes(s, notes, rating)
                                }
                            },
                            placeholder = {
                                Text(
                                    "E.g., Felt nervous during the first 30s, improved pace when answering Q&A prompt.",
                                    color = TextMuted,
                                    fontSize = 13.sp
                                )
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = DarkSurface,
                                unfocusedContainerColor = DarkSurface,
                                focusedBorderColor = ElectricCyan,
                                unfocusedBorderColor = DarkSurfaceHighlight,
                                focusedTextColor = TextPrimary,
                                unfocusedTextColor = TextPrimary
                            ),
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                                .testTag("session_notes_input")
                        )
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = onPracticeAgain,
                    colors = ButtonDefaults.buttonColors(containerColor = NeonPink),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .testTag("practice_again_button")
                ) {
                    Icon(
                        imageVector = AppIcons.Replay,
                        contentDescription = null,
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Practice Again",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedButton(
                    onClick = onDone,
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(1.dp, DarkSurfaceHighlight),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("back_to_dashboard_button")
                ) {
                    Text(
                        text = "Return to Dashboard",
                        color = TextSecondary,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
private fun MetricCard(
    title: String,
    value: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    accentColor: Color,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = DarkSurfaceElevated),
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(14.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    color = TextSecondary,
                    fontSize = 12.sp
                )
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = accentColor,
                    modifier = Modifier.size(18.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = value,
                color = TextPrimary,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
    }
}

private fun calculateEngagementScore(
    durationSeconds: Long,
    likes: Int,
    comments: Int,
    peakViewers: Int
): Int {
    if (durationSeconds <= 0) return 75
    val timeFactor = (durationSeconds / 30).toInt().coerceIn(1, 10)
    val activity = (likes * 0.1 + comments * 2.0).toInt()
    val base = 70 + (activity / (timeFactor + 1)).coerceIn(0, 28)
    return base.coerceIn(70, 99)
}
