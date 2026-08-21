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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import com.livepractice.simulator.data.database.SessionLog
import com.livepractice.simulator.ui.theme.*
import com.livepractice.simulator.ui.viewmodel.SimulationViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    viewModel: SimulationViewModel,
    onNavigateBack: () -> Unit
) {
    val sessions by viewModel.allSessions.collectAsStateWithLifecycle()
    var sessionToDelete by remember { mutableStateOf<SessionLog?>(null) }
    var selectedSessionDetail by remember { mutableStateOf<SessionLog?>(null) }

    Scaffold(
        containerColor = DarkBackground,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Practice History",
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary,
                        fontSize = 18.sp
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onNavigateBack,
                        modifier = Modifier.testTag("history_back_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = TextPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = DarkSurface
                )
            )
        }
    ) { paddingValues ->
        if (sessions.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(72.dp)
                            .clip(CircleShape)
                            .background(DarkSurfaceElevated),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = AppIcons.HistoryToggleOff,
                            contentDescription = null,
                            tint = TextMuted,
                            modifier = Modifier.size(36.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "No Practice Logs Yet",
                        color = TextPrimary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Complete your first simulated live broadcast to see detailed analytics and reflection logs saved here.",
                        color = TextSecondary,
                        fontSize = 13.sp,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {
                item {
                    Text(
                        text = "${sessions.size} Recorded Streams",
                        color = TextSecondary,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                items(sessions, key = { it.id }) { session ->
                    SessionHistoryCard(
                        session = session,
                        onClick = { selectedSessionDetail = session },
                        onDelete = { sessionToDelete = session }
                    )
                }
            }
        }

        if (sessionToDelete != null) {
            AlertDialog(
                onDismissRequest = { sessionToDelete = null },
                containerColor = DarkSurfaceElevated,
                title = {
                    Text(
                        text = "Delete Session Log?",
                        color = TextPrimary,
                        fontWeight = FontWeight.Bold
                    )
                },
                text = {
                    Text(
                        text = "Are you sure you want to delete this recorded session? This cannot be undone.",
                        color = TextSecondary,
                        fontSize = 14.sp
                    )
                },
                confirmButton = {
                    Button(
                        onClick = {
                            sessionToDelete?.let { viewModel.deleteSession(it) }
                            sessionToDelete = null
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = LiveRed),
                        modifier = Modifier.testTag("confirm_delete_button")
                    ) {
                        Text("Delete", color = Color.White)
                    }
                },
                dismissButton = {
                    TextButton(onClick = { sessionToDelete = null }) {
                        Text("Cancel", color = TextSecondary)
                    }
                }
            )
        }

        if (selectedSessionDetail != null) {
            val s = selectedSessionDetail!!
            val dateFormat = SimpleDateFormat("MMM dd, yyyy • hh:mm a", Locale.getDefault())
            val formattedDate = dateFormat.format(Date(s.dateTimestamp))

            AlertDialog(
                onDismissRequest = { selectedSessionDetail = null },
                containerColor = DarkSurfaceElevated,
                title = {
                    Column {
                        Text(
                            text = s.title,
                            color = TextPrimary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        Text(
                            text = formattedDate,
                            color = TextSecondary,
                            fontSize = 12.sp
                        )
                    }
                },
                text = {
                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Rating:", color = TextSecondary, fontSize = 13.sp)
                            Text("★ ${s.performanceRating}/5", color = GoldenBadge, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Duration:", color = TextSecondary, fontSize = 13.sp)
                            Text("${s.durationSeconds / 60}m ${s.durationSeconds % 60}s", color = TextPrimary, fontSize = 13.sp)
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Peak Viewers:", color = TextSecondary, fontSize = 13.sp)
                            Text("${s.peakViewers}", color = TextPrimary, fontSize = 13.sp)
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Likes Received:", color = TextSecondary, fontSize = 13.sp)
                            Text("${s.totalLikes}", color = TextPrimary, fontSize = 13.sp)
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Comments:", color = TextSecondary, fontSize = 13.sp)
                            Text("${s.totalComments}", color = TextPrimary, fontSize = 13.sp)
                        }

                        if (s.userNotes.isNotBlank()) {
                            HorizontalDivider(color = DarkSurfaceHighlight)
                            Text("Notes & Reflections:", color = TextSecondary, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            Text(
                                text = s.userNotes,
                                color = TextPrimary,
                                fontSize = 13.sp,
                                lineHeight = 17.sp
                            )
                        }
                    }
                },
                confirmButton = {
                    TextButton(onClick = { selectedSessionDetail = null }) {
                        Text("Close", color = ElectricCyan)
                    }
                }
            )
        }
    }
}

@Composable
private fun SessionHistoryCard(
    session: SessionLog,
    onClick: () -> Unit,
    onDelete: () -> Unit
) {
    val dateFormat = SimpleDateFormat("MMM dd, yyyy • hh:mm a", Locale.getDefault())
    val formattedDate = dateFormat.format(Date(session.dateTimestamp))

    Card(
        colors = CardDefaults.cardColors(containerColor = DarkSurface),
        shape = RoundedCornerShape(14.dp),
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, DarkSurfaceHighlight, RoundedCornerShape(14.dp))
            .clickable { onClick() }
            .testTag("session_card_${session.id}")
    ) {
        Column(
            modifier = Modifier.padding(14.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = session.title,
                        color = TextPrimary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                    Text(
                        text = formattedDate,
                        color = TextMuted,
                        fontSize = 11.sp
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(GoldenBadge.copy(alpha = 0.2f))
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text(
                            text = "★ ${session.performanceRating}/5",
                            color = GoldenBadge,
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp
                        )
                    }

                    IconButton(
                        onClick = onDelete,
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            imageVector = AppIcons.DeleteOutline,
                            contentDescription = "Delete",
                            tint = TextMuted,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "⏱️ ${session.durationSeconds / 60}m ${session.durationSeconds % 60}s",
                    color = TextSecondary,
                    fontSize = 12.sp
                )
                Text(
                    text = "👥 ${session.peakViewers} peak",
                    color = TextSecondary,
                    fontSize = 12.sp
                )
                Text(
                    text = "❤️ ${session.totalLikes} likes",
                    color = TextSecondary,
                    fontSize = 12.sp
                )
            }

            if (session.userNotes.isNotBlank()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "“${session.userNotes}”",
                    color = TextMuted,
                    fontSize = 12.sp,
                    maxLines = 2
                )
            }
        }
    }
}
