package com.livepractice.simulator.ui.viewmodel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.livepractice.simulator.data.ScenarioProvider
import com.livepractice.simulator.data.database.SessionLog
import com.livepractice.simulator.data.database.SessionRepository
import com.livepractice.simulator.data.model.GiftAnimationEvent
import com.livepractice.simulator.data.model.HeartParticle
import com.livepractice.simulator.data.model.LiveComment
import com.livepractice.simulator.data.model.LiveScenario
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.util.UUID
import kotlin.random.Random

class SimulationViewModel(
    private val repository: SessionRepository
) : ViewModel() {

    val allSessions: StateFlow<List<SessionLog>> = repository.allSessions
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val sessionCount: StateFlow<Int> = repository.sessionCount
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0
        )

    val totalPracticeSeconds: StateFlow<Long?> = repository.totalPracticeSeconds
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0L
        )

    private val _currentScenario = MutableStateFlow(ScenarioProvider.defaultScenarios.first())
    val currentScenario: StateFlow<LiveScenario> = _currentScenario.asStateFlow()

    private val _isStreaming = MutableStateFlow(false)
    val isStreaming: StateFlow<Boolean> = _isStreaming.asStateFlow()

    private val _durationSeconds = MutableStateFlow(0L)
    val durationSeconds: StateFlow<Long> = _durationSeconds.asStateFlow()

    private val _viewerCount = MutableStateFlow(1850)
    val viewerCount: StateFlow<Int> = _viewerCount.asStateFlow()

    private val _peakViewers = MutableStateFlow(1850)
    val peakViewers: StateFlow<Int> = _peakViewers.asStateFlow()

    private val _likesCount = MutableStateFlow(0)
    val likesCount: StateFlow<Int> = _likesCount.asStateFlow()

    private val _giftCount = MutableStateFlow(0)
    val giftCount: StateFlow<Int> = _giftCount.asStateFlow()

    private val _comments = MutableStateFlow<List<LiveComment>>(emptyList())
    val comments: StateFlow<List<LiveComment>> = _comments.asStateFlow()

    private val _pinnedComment = MutableStateFlow<LiveComment?>(null)
    val pinnedComment: StateFlow<LiveComment?> = _pinnedComment.asStateFlow()

    private val _activeChallenge = MutableStateFlow<String?>(null)
    val activeChallenge: StateFlow<String?> = _activeChallenge.asStateFlow()

    private val _activeGiftAnimation = MutableStateFlow<GiftAnimationEvent?>(null)
    val activeGiftAnimation: StateFlow<GiftAnimationEvent?> = _activeGiftAnimation.asStateFlow()

    private val _heartParticles = MutableStateFlow<List<HeartParticle>>(emptyList())
    val heartParticles: StateFlow<List<HeartParticle>> = _heartParticles.asStateFlow()

    private val _isMuted = MutableStateFlow(false)
    val isMuted: StateFlow<Boolean> = _isMuted.asStateFlow()

    private val _isCameraEnabled = MutableStateFlow(true)
    val isCameraEnabled: StateFlow<Boolean> = _isCameraEnabled.asStateFlow()

    private val _isFrontCamera = MutableStateFlow(true)
    val isFrontCamera: StateFlow<Boolean> = _isFrontCamera.asStateFlow()

    private val _virtualBgIndex = MutableStateFlow(0)
    val virtualBgIndex: StateFlow<Int> = _virtualBgIndex.asStateFlow()

    private val _lastCompletedSession = MutableStateFlow<SessionLog?>(null)
    val lastCompletedSession: StateFlow<SessionLog?> = _lastCompletedSession.asStateFlow()

    private var streamJob: Job? = null
    private var commentJob: Job? = null
    private var likeJob: Job? = null
    private var challengeJob: Job? = null

    fun selectScenario(scenario: LiveScenario) {
        _currentScenario.value = scenario
        _viewerCount.value = scenario.baseViewers
        _peakViewers.value = scenario.baseViewers
    }

    fun startStream(scenario: LiveScenario? = null) {
        if (scenario != null) {
            _currentScenario.value = scenario
        }
        val currentSc = _currentScenario.value

        _isStreaming.value = true
        _durationSeconds.value = 0L
        _viewerCount.value = currentSc.baseViewers
        _peakViewers.value = currentSc.baseViewers
        _likesCount.value = 0
        _giftCount.value = 0
        _pinnedComment.value = null
        _comments.value = listOf(
            LiveComment(
                id = UUID.randomUUID().toString(),
                username = "System",
                text = "🔴 Live stream started. Simulated audience connected!",
                avatarColorHex = 0xFFFF2A6D,
                isPinned = true
            )
        )
        _activeChallenge.value = currentSc.promptChallenges.firstOrNull()

        startSimulationLoops(currentSc)
    }

    private fun startSimulationLoops(scenario: LiveScenario) {
        streamJob?.cancel()
        streamJob = viewModelScope.launch {
            while (isActive && _isStreaming.value) {
                delay(1000L)
                _durationSeconds.value += 1
                
                val fluctuation = Random.nextInt(-15, 25)
                val newViewers = (_viewerCount.value + fluctuation).coerceAtLeast(scenario.baseViewers / 2)
                _viewerCount.value = newViewers
                if (newViewers > _peakViewers.value) {
                    _peakViewers.value = newViewers
                }
            }
        }

        commentJob?.cancel()
        commentJob = viewModelScope.launch {
            while (isActive && _isStreaming.value) {
                val delayTime = (scenario.commentPaceMs * Random.nextDouble(0.7, 1.4)).toLong()
                delay(delayTime)

                val username = ScenarioProvider.randomUsernames.random()
                val avatarColor = ScenarioProvider.randomAvatars.random()
                val text = scenario.sampleComments.random()
                val isQuestion = text.contains("?")
                val isSuper = Random.nextInt(100) < 8

                val comment = LiveComment(
                    id = UUID.randomUUID().toString(),
                    username = username,
                    text = text,
                    avatarColorHex = avatarColor,
                    isQuestion = isQuestion,
                    isSuperChat = isSuper,
                    superAmount = if (isSuper) "$${listOf(2, 5, 10, 20, 50).random()}" else null,
                    verified = Random.nextInt(100) < 15
                )

                _comments.value = (_comments.value + comment).takeLast(40)

                if (Random.nextInt(100) < 12) {
                    triggerSimulatedGift(username)
                }
            }
        }

        likeJob?.cancel()
        likeJob = viewModelScope.launch {
            while (isActive && _isStreaming.value) {
                delay(Random.nextLong(300L, 800L))
                val likesBurst = Random.nextInt(1, 6)
                _likesCount.value += likesBurst
                repeat(likesBurst) {
                    emitHeartParticle()
                }
            }
        }

        challengeJob?.cancel()
        challengeJob = viewModelScope.launch {
            var promptIndex = 0
            while (isActive && _isStreaming.value) {
                delay(25000L)
                if (scenario.promptChallenges.isNotEmpty()) {
                    promptIndex = (promptIndex + 1) % scenario.promptChallenges.size
                    _activeChallenge.value = scenario.promptChallenges[promptIndex]
                }
            }
        }
    }

    private fun triggerSimulatedGift(username: String) {
        val gift = ScenarioProvider.simulatedGifts.random()
        _giftCount.value += 1
        val event = GiftAnimationEvent(
            id = UUID.randomUUID().toString(),
            username = username,
            giftName = gift.first,
            giftEmoji = gift.second,
            coinValue = gift.third
        )
        _activeGiftAnimation.value = event

        val giftComment = LiveComment(
            id = UUID.randomUUID().toString(),
            username = username,
            text = "sent a ${gift.first} ${gift.second} (${gift.third} coins)!",
            avatarColorHex = 0xFFFFB703,
            giftEmoji = gift.second,
            isSuperChat = true,
            superAmount = "${gift.third} 🪙"
        )
        _comments.value = (_comments.value + giftComment).takeLast(40)

        viewModelScope.launch {
            delay(3500L)
            if (_activeGiftAnimation.value?.id == event.id) {
                _activeGiftAnimation.value = null
            }
        }
    }

    fun triggerAudienceLike() {
        _likesCount.value += 1
        emitHeartParticle()
    }

    private fun emitHeartParticle() {
        val colors = listOf(
            Color(0xFFFF2A6D),
            Color(0xFF00F0FF),
            Color(0xFFFFE600),
            Color(0xFFFF0055),
            Color(0xFF9D4EDD),
            Color(0xFF00E676)
        )
        val particle = HeartParticle(
            id = System.nanoTime(),
            startXFraction = Random.nextFloat(),
            color = colors.random(),
            emoji = listOf("❤️", "💖", "🔥", "👏", "✨", "💯").random()
        )
        _heartParticles.value = (_heartParticles.value + particle).takeLast(25)

        viewModelScope.launch {
            delay(2000L)
            _heartParticles.value = _heartParticles.value.filter { it.id != particle.id }
        }
    }

    fun pinComment(comment: LiveComment) {
        _pinnedComment.value = if (_pinnedComment.value?.id == comment.id) null else comment
    }

    fun unpinComment() {
        _pinnedComment.value = null
    }

    fun dismissChallenge() {
        _activeChallenge.value = null
    }

    fun nextChallenge() {
        val currentSc = _currentScenario.value
        val challenges = currentSc.promptChallenges
        if (challenges.isNotEmpty()) {
            val currentIndex = challenges.indexOf(_activeChallenge.value)
            val nextIndex = (currentIndex + 1) % challenges.size
            _activeChallenge.value = challenges[nextIndex]
        }
    }

    fun addCustomUserComment(text: String) {
        if (text.isBlank()) return
        val userComment = LiveComment(
            id = UUID.randomUUID().toString(),
            username = "Host (You)",
            text = text.trim(),
            avatarColorHex = 0xFF00F0FF,
            verified = true
        )
        _comments.value = (_comments.value + userComment).takeLast(40)
    }

    fun toggleMute() {
        _isMuted.value = !_isMuted.value
    }

    fun toggleCamera() {
        _isCameraEnabled.value = !_isCameraEnabled.value
    }

    fun switchCamera() {
        _isFrontCamera.value = !_isFrontCamera.value
    }

    fun cycleVirtualBackground() {
        _virtualBgIndex.value = (_virtualBgIndex.value + 1) % 4
    }

    fun endStream(rating: Int = 5, notes: String = ""): SessionLog {
        _isStreaming.value = false
        streamJob?.cancel()
        commentJob?.cancel()
        likeJob?.cancel()
        challengeJob?.cancel()

        val completed = SessionLog(
            title = "${_currentScenario.value.title} Practice",
            scenarioName = _currentScenario.value.title,
            durationSeconds = _durationSeconds.value,
            peakViewers = _peakViewers.value,
            totalLikes = _likesCount.value,
            totalComments = _comments.value.size,
            giftsReceived = _giftCount.value,
            performanceRating = rating,
            userNotes = notes
        )
        _lastCompletedSession.value = completed

        viewModelScope.launch {
            val id = repository.insertSession(completed)
            _lastCompletedSession.value = completed.copy(id = id)
        }

        return completed
    }

    fun updateSessionNotes(session: SessionLog, notes: String, rating: Int) {
        viewModelScope.launch {
            repository.insertSession(session.copy(userNotes = notes, performanceRating = rating))
            if (_lastCompletedSession.value?.id == session.id) {
                _lastCompletedSession.value = session.copy(userNotes = notes, performanceRating = rating)
            }
        }
    }

    fun deleteSession(session: SessionLog) {
        viewModelScope.launch {
            repository.deleteSession(session)
        }
    }
}

class SimulationViewModelFactory(
    private val repository: SessionRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SimulationViewModel::class.java)) {
            return SimulationViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
