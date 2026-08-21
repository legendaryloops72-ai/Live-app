package com.livepractice.simulator.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.livepractice.simulator.data.model.HeartParticle
import kotlin.math.sin

@Composable
fun FloatingHeartsView(
    particles: List<HeartParticle>,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .width(80.dp)
    ) {
        particles.forEach { particle ->
            FloatingSingleHeart(particle = particle)
        }
    }
}

@Composable
private fun BoxScope.FloatingSingleHeart(particle: HeartParticle) {
    val animState = remember { Animatable(0f) }

    LaunchedEffect(particle.id) {
        animState.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 2000,
                easing = FastOutSlowInEasing
            )
        )
    }

    val progress = animState.value
    val yOffset = -1 * (progress * 380)
    val alpha = (1f - progress).coerceIn(0f, 1f)
    val scale = if (progress < 0.2f) (progress / 0.2f) * 1.2f else (1.2f - (progress - 0.2f) * 0.3f)
    val sway = (sin(progress * Math.PI * 4) * 20 * (particle.startXFraction - 0.5f) * 2).toFloat()

    Box(
        modifier = Modifier
            .align(Alignment.BottomCenter)
            .offset(
                x = (particle.startXFraction * 40 - 20 + sway).dp,
                y = yOffset.dp
            )
            .graphicsLayer {
                this.alpha = alpha
                this.scaleX = scale
                this.scaleY = scale
            }
    ) {
        Text(
            text = particle.emoji,
            fontSize = 24.sp
        )
    }
}
