package com.livepractice.simulator.ui.screens

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.livepractice.simulator.ui.components.*
import com.livepractice.simulator.ui.theme.*
import com.livepractice.simulator.ui.viewmodel.SimulationViewModel

@Composable
fun LiveStreamScreen(
    viewModel: SimulationViewModel,
    onStreamFinished: () -> Unit,
    onNavigateBack: () -> Unit
) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    val currentScenario by viewModel.currentScenario.collectAsStateWithLifecycle()
    val isStreaming by viewModel.isStreaming.collectAsStateWithLifecycle()
    val durationSeconds by viewModel.durationSeconds.collectAsStateWithLifecycle()
    val viewerCount by viewModel.viewerCount.collectAsStateWithLifecycle()
    val comments by viewModel.comments.collectAsStateWithLifecycle()
    val pinnedComment by viewModel.pinnedComment.collectAsStateWithLifecycle()
    val activeChallenge by viewModel.activeChallenge.collectAsStateWithLifecycle()
    val activeGift by viewModel.activeGiftAnimation.collectAsStateWithLifecycle()
    val heartParticles by viewModel.heartParticles.collectAsStateWithLifecycle()
    val isMuted by viewModel.isMuted.collectAsStateWithLifecycle()
    val isCameraEnabled by viewModel.isCameraEnabled.collectAsStateWithLifecycle()
    val isFrontCamera by viewModel.isFrontCamera.collectAsStateWithLifecycle()
    val virtualBgIndex by viewModel.virtualBgIndex.collectAsStateWithLifecycle()

    var showEndDialog by remember { mutableStateOf(false) }
    var hostInputText by remember { mutableStateOf("") }
    val listState = rememberLazyListState()

    var hasCameraPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { granted ->
            hasCameraPermission = granted
        }
    )

    LaunchedEffect(Unit) {
        if (!hasCameraPermission && isCameraEnabled) {
            permissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    LaunchedEffect(comments.size) {
        if (comments.isNotEmpty()) {
            listState.animateScrollToItem(comments.size - 1)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        CameraPreview(
            isCameraEnabled = isCameraEnabled && hasCameraPermission,
            isFrontCamera = isFrontCamera,
            virtualBgIndex = virtualBgIndex,
            isMuted = isMuted
        )

        FloatingHeartsView(
            particles = heartParticles,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 80.dp, end = 12.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                LiveHeaderBar(
                    scenarioTitle = currentScenario.title,
                    viewerCount = viewerCount,
                    durationSeconds = durationSeconds,
                    isCameraEnabled = isCameraEnabled,
                    isMuted = isMuted,
                    onToggleCamera = { viewModel.toggleCamera() },
                    onSwitchCamera = { viewModel.switchCamera() },
                    onToggleMute = { viewModel.toggleMute() },
                    onCycleBackground = { viewModel.cycleVirtualBackground() },
                    onEndLive = { showEndDialog = true }
                )

                GiftBanner(
                    event = activeGift,
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 4.dp)
                )

                ChallengeCard(
                    challengeText = activeChallenge,
                    onNext = { viewModel.nextChallenge() },
                    onDismiss = { viewModel.dismissChallenge() },
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 4.dp)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                if (pinnedComment != null) {
                    LiveCommentItem(
                        comment = pinnedComment!!,
                        isPinned = true,
                        onPinClick = { viewModel.unpinComment() }
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                }

                LazyColumn(
                    state = listState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 220.dp),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    items(comments, key = { it.id }) { comment ->
                        LiveCommentItem(
                            comment = comment,
                            isPinned = pinnedComment?.id == comment.id,
                            onPinClick = { viewModel.pinComment(comment) }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = hostInputText,
                        onValueChange = { hostInputText = it },
                        placeholder = {
                            Text(
                                "Speak or type as Host...",
                                color = TextMuted,
                                fontSize = 13.sp
                            )
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
                        keyboardActions = KeyboardActions(
                            onSend = {
                                if (hostInputText.isNotBlank()) {
                                    viewModel.addCustomUserComment(hostInputText)
                                    hostInputText = ""
                                    focusManager.clearFocus()
                                }
                            }
                        ),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = DarkSurfaceElevated.copy(alpha = 0.8f),
                            unfocusedContainerColor = DarkSurfaceElevated.copy(alpha = 0.6f),
                            focusedBorderColor = ElectricCyan,
                            unfocusedBorderColor = Color.Transparent,
                            focusedTextColor = TextPrimary,
                            unfocusedTextColor = TextPrimary
                        ),
                        shape = RoundedCornerShape(24.dp),
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp)
                            .testTag("host_comment_input")
                    )

                    if (hostInputText.isNotBlank()) {
                        IconButton(
                            onClick = {
                                viewModel.addCustomUserComment(hostInputText)
                                hostInputText = ""
                                focusManager.clearFocus()
                            },
                            modifier = Modifier
                                .size(44.dp)
                                .clip(CircleShape)
                                .background(ElectricCyan)
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.Send,
                                contentDescription = "Send",
                                tint = Color.Black,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }

                    IconButton(
                        onClick = { viewModel.nextChallenge() },
                        modifier = Modifier
                            .size(44.dp)
                            .clip(CircleShape)
                            .background(DarkSurfaceElevated.copy(alpha = 0.8f))
                            .border(1.dp, DarkSurfaceHighlight, CircleShape)
                            .testTag("next_prompt_button")
                    ) {
                        Icon(
                            imageVector = AppIcons.Psychology,
                            contentDescription = "Next Cue",
                            tint = BrightYellow,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    IconButton(
                        onClick = { viewModel.triggerAudienceLike() },
                        modifier = Modifier
                            .size(44.dp)
                            .clip(CircleShape)
                            .background(NeonPink)
                            .testTag("like_burst_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Likes",
                            tint = Color.White,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                }
            }
        }

        if (showEndDialog) {
            AlertDialog(
                onDismissRequest = { showEndDialog = false },
                containerColor = DarkSurfaceElevated,
                title = {
                    Text(
                        text = "End Live Practice?",
                        color = TextPrimary,
                        fontWeight = FontWeight.Bold
                    )
                },
                text = {
                    Text(
                        text = "Your practice session stats (peak viewers: $viewerCount, duration: ${durationSeconds / 60}m ${durationSeconds % 60}s) will be calculated and saved.",
                        color = TextSecondary,
                        fontSize = 14.sp
                    )
                },
                confirmButton = {
                    Button(
                        onClick = {
                            showEndDialog = false
                            viewModel.endStream()
                            onStreamFinished()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = LiveRed),
                        modifier = Modifier.testTag("confirm_end_stream_button")
                    ) {
                        Text("End & Review", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = { showEndDialog = false }
                    ) {
                        Text("Continue Live", color = TextSecondary)
                    }
                }
            )
        }
    }
}
