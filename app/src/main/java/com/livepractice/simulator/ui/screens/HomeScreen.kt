package com.livepractice.simulator.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.livepractice.simulator.data.ScenarioProvider
import com.livepractice.simulator.data.model.LiveScenario
import com.livepractice.simulator.ui.theme.*
import com.livepractice.simulator.ui.viewmodel.SimulationViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: SimulationViewModel,
    onStartLive: () -> Unit,
    onNavigateToHistory: () -> Unit
) {
    val currentScenario by viewModel.currentScenario.collectAsStateWithLifecycle()
    val sessionCount by viewModel.sessionCount.collectAsStateWithLifecycle()
    val totalSeconds by viewModel.totalPracticeSeconds.collectAsStateWithLifecycle()
    val recentSessions by viewModel.allSessions.collectAsStateWithLifecycle()
    val isCameraEnabled by viewModel.isCameraEnabled.collectAsStateWithLifecycle()

    val totalMinutes = (totalSeconds ?: 0L) / 60

    Scaffold(
        containerColor = DarkBackground,
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(28.dp)
                                .clip(CircleShape)
                                .background(NeonPink),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = AppIcons.Videocam,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                        Text(
                            text = "Live Practice Simulator",
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary,
                            fontSize = 18.sp
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = onNavigateToHistory,
                        modifier = Modifier.testTag("history_button")
                    ) {
                        Icon(
                            imageVector = AppIcons.History,
                            contentDescription = "Session History",
                            tint = ElectricCyan
                        )
                    }
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
                StatsOverviewCard(
                    sessionCount = sessionCount,
                    totalMinutes = totalMinutes,
                    onHistoryClick = onNavigateToHistory
                )
            }

            item {
                StudioModeSelectorCard(
                    isCameraEnabled = isCameraEnabled,
                    onToggleCamera = { viewModel.toggleCamera() }
                )
            }

            item {
                Text(
                    text = "Select Streaming Scenario",
                    color = TextPrimary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )
                Text(
                    text = "Pick an audience style to simulate real-time chat, questions, and gifts.",
                    color = TextSecondary,
                    fontSize = 13.sp
                )
            }

            items(ScenarioProvider.defaultScenarios) { scenario ->
                ScenarioCard(
                    scenario = scenario,
                    isSelected = currentScenario.id == scenario.id,
                    onSelect = {
                        viewModel.selectScenario(scenario)
                    },
                    onLaunch = {
                        viewModel.selectScenario(scenario)
                        viewModel.startStream(scenario)
                        onStartLive()
                    }
                )
            }

            if (recentSessions.isNotEmpty()) {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Recent Sessions",
                            color = TextPrimary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                        Text(
                            text = "View All (${recentSessions.size})",
                            color = ElectricCyan,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.clickable { onNavigateToHistory() }
                        )
                    }
                }

                item {
                    val latest = recentSessions.first()
                    Card(
                        colors = CardDefaults.cardColors(containerColor = DarkSurfaceElevated),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = latest.title,
                                    color = TextPrimary,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 14.sp
                                )
                                Text(
                                    text = "${latest.durationSeconds / 60}m ${latest.durationSeconds % 60}s • ${latest.peakViewers} peak viewers • ${latest.totalLikes} likes",
                                    color = TextSecondary,
                                    fontSize = 12.sp
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(GoldenBadge.copy(alpha = 0.2f))
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = "★ ${latest.performanceRating}/5",
                                    color = GoldenBadge,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp
                                )
                            }
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = {
                        viewModel.startStream(currentScenario)
                        onStartLive()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = NeonPink
                    ),
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp)
                        .testTag("start_live_button")
                ) {
                    Icon(
                        imageVector = AppIcons.Videocam,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(22.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "GO LIVE WITH ${currentScenario.title.uppercase()}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
private fun StatsOverviewCard(
    sessionCount: Int,
    totalMinutes: Long,
    onHistoryClick: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = DarkSurface),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, DarkSurfaceHighlight, RoundedCornerShape(16.dp))
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Practice Performance",
                    color = TextSecondary,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "Tracked in Room DB",
                    color = LiveGreen,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                StatColumn(
                    value = sessionCount.toString(),
                    label = "Total Streams",
                    accentColor = NeonPink
                )
                VerticalDivider(
                    color = DarkSurfaceHighlight,
                    modifier = Modifier
                        .height(40.dp)
                        .width(1.dp)
                )
                StatColumn(
                    value = "${totalMinutes}m",
                    label = "Practice Time",
                    accentColor = ElectricCyan
                )
                VerticalDivider(
                    color = DarkSurfaceHighlight,
                    modifier = Modifier
                        .height(40.dp)
                        .width(1.dp)
                )
                StatColumn(
                    value = if (sessionCount > 0) "Active" else "Ready",
                    label = "Studio Status",
                    accentColor = BrightYellow
                )
            }
        }
    }
}

@Composable
private fun StatColumn(
    value: String,
    label: String,
    accentColor: Color
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            color = accentColor,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Text(
            text = label,
            color = TextSecondary,
            fontSize = 11.sp
        )
    }
}

@Composable
private fun StudioModeSelectorCard(
    isCameraEnabled: Boolean,
    onToggleCamera: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = DarkSurfaceElevated),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Icon(
                    imageVector = if (isCameraEnabled) AppIcons.CameraAlt else Icons.Default.Person,
                    contentDescription = null,
                    tint = if (isCameraEnabled) ElectricCyan else PurpleAccent,
                    modifier = Modifier.size(22.dp)
                )
                Column {
                    Text(
                        text = if (isCameraEnabled) "Camera Video Stream" else "Virtual Studio Avatar",
                        color = TextPrimary,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp
                    )
                    Text(
                        text = if (isCameraEnabled) "Front/Back camera live feed" else "Simulated digital backdrop",
                        color = TextSecondary,
                        fontSize = 11.sp
                    )
                }
            }

            Switch(
                checked = isCameraEnabled,
                onCheckedChange = { onToggleCamera() },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = ElectricCyan,
                    uncheckedThumbColor = TextSecondary,
                    uncheckedTrackColor = DarkSurfaceHighlight
                )
            )
        }
    }
}

@Composable
private fun ScenarioCard(
    scenario: LiveScenario,
    isSelected: Boolean,
    onSelect: () -> Unit,
    onLaunch: () -> Unit
) {
    val borderColor = if (isSelected) NeonPink else DarkSurfaceHighlight
    val containerColor = if (isSelected) DarkSurfaceElevated else DarkSurface

    Card(
        colors = CardDefaults.cardColors(containerColor = containerColor),
        shape = RoundedCornerShape(14.dp),
        modifier = Modifier
            .fillMaxWidth()
            .border(if (isSelected) 1.5.dp else 1.dp, borderColor, RoundedCornerShape(14.dp))
            .clickable { onSelect() }
            .testTag("scenario_card_${scenario.id}")
    ) {
        Column(
            modifier = Modifier.padding(14.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(text = scenario.icon, fontSize = 22.sp)
                    Column {
                        Text(
                            text = scenario.title,
                            color = TextPrimary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                        Text(
                            text = scenario.category,
                            color = TextSecondary,
                            fontSize = 11.sp
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(
                            when (scenario.difficulty) {
                                "Beginner" -> LiveGreen.copy(alpha = 0.2f)
                                "Intermediate" -> BrightYellow.copy(alpha = 0.2f)
                                else -> LiveRed.copy(alpha = 0.2f)
                            }
                        )
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = scenario.difficulty,
                        color = when (scenario.difficulty) {
                            "Beginner" -> LiveGreen
                            "Intermediate" -> BrightYellow
                            else -> LiveRed
                        },
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = scenario.description,
                color = TextSecondary,
                fontSize = 12.sp,
                lineHeight = 16.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "👥 ~${scenario.baseViewers} viewers",
                        color = TextMuted,
                        fontSize = 11.sp
                    )
                    Text(
                        text = "⚡ ${scenario.promptChallenges.size} practice cues",
                        color = TextMuted,
                        fontSize = 11.sp
                    )
                }

                if (isSelected) {
                    TextButton(
                        onClick = onLaunch,
                        colors = ButtonDefaults.textButtonColors(contentColor = NeonPink),
                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp)
                    ) {
                        Text(
                            text = "Launch Now →",
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }
    }
}
