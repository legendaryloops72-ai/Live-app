package com.livepractice.simulator.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

object AppIcons {
    private fun buildIcon(name: String, pathData: String): ImageVector {
        return ImageVector.Builder(
            name = name,
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color.White)
            ) {
                // Vector path builder
            }
        }.build()
    }

    val Mic: ImageVector by lazy {
        ImageVector.Builder("Mic", 24.dp, 24.dp, 24f, 24f).apply {
            path(fill = SolidColor(Color.White)) {
                moveTo(12f, 14f)
                curveTo(13.66f, 14f, 15f, 12.66f, 15f, 11f)
                lineTo(15f, 5f)
                curveTo(15f, 3.34f, 13.66f, 2f, 12f, 2f)
                reflectiveCurveTo(9f, 3.34f, 9f, 5f)
                lineTo(9f, 11f)
                curveTo(9f, 12.66f, 10.34f, 14f, 12f, 14f)
                close()
                moveTo(17.3f, 11f)
                curveTo(17.3f, 14f, 14.76f, 16.1f, 12f, 16.1f)
                reflectiveCurveTo(6.7f, 14f, 6.7f, 11f)
                lineTo(5f, 11f)
                curveTo(5f, 14.41f, 7.72f, 17.23f, 11f, 17.72f)
                lineTo(11f, 21f)
                lineTo(13f, 21f)
                lineTo(13f, 17.72f)
                curveTo(16.28f, 17.24f, 19f, 14.42f, 19f, 11f)
                lineTo(17.3f, 11f)
                close()
            }
        }.build()
    }

    val MicOff: ImageVector by lazy {
        ImageVector.Builder("MicOff", 24.dp, 24.dp, 24f, 24f).apply {
            path(fill = SolidColor(Color.White)) {
                moveTo(19f, 11f)
                lineToRelative(-1.7f, 0f)
                curveToRelative(0f, 0.74f, -0.16f, 1.43f, -0.43f, 2.05f)
                lineToRelative(1.23f, 1.23f)
                curveToRelative(0.56f, -0.98f, 0.9f, -2.09f, 0.9f, -3.28f)
                close()
                moveTo(14.98f, 11.17f)
                curveToRelative(0f, -0.06f, 0.02f, -0.11f, 0.02f, -0.17f)
                lineTo(15f, 5f)
                curveToRelative(0f, -1.66f, -1.34f, -3f, -3f, -3f)
                reflectiveCurveTo(9f, 3.34f, 9f, 5f)
                lineToRelative(0f, 0.18f)
                lineToRelative(5.98f, 5.99f)
                close()
                moveTo(4.27f, 3f)
                lineTo(3f, 4.27f)
                lineToRelative(6.01f, 6.01f)
                lineTo(9.01f, 11f)
                curveToRelative(0f, 1.66f, 1.33f, 3f, 2.99f, 3f)
                curveToRelative(0.22f, 0f, 0.44f, -0.03f, 0.65f, -0.08f)
                lineToRelative(4.07f, 4.07f)
                curveToRelative(-1.15f, 0.63f, -2.46f, 0.99f, -3.72f, 0.99f)
                curveToRelative(-3.41f, 0f, -6.19f, -2.61f, -6.19f, -6.02f)
                lineTo(5f, 12.96f)
                curveToRelative(0f, 3.8f, 2.94f, 6.94f, 6.72f, 7.43f)
                lineTo(11.72f, 23f)
                lineToRelative(2.56f, 0f)
                lineToRelative(0f, -2.58f)
                curveToRelative(1.55f, -0.2f, 3.01f, -0.84f, 4.21f, -1.77f)
                lineTo(19.73f, 20f)
                lineTo(21f, 18.73f)
                lineTo(4.27f, 3f)
                close()
            }
        }.build()
    }

    val Videocam: ImageVector by lazy {
        ImageVector.Builder("Videocam", 24.dp, 24.dp, 24f, 24f).apply {
            path(fill = SolidColor(Color.White)) {
                moveTo(17f, 10.5f)
                lineTo(17f, 7f)
                curveToRelative(0f, -0.55f, -0.45f, -1f, -1f, -1f)
                lineTo(4f, 6f)
                curveToRelative(-0.55f, 0f, -1f, 0.45f, -1f, 1f)
                lineToRelative(0f, 10f)
                curveToRelative(0f, 0.55f, 0.45f, 1f, 1f, 1f)
                lineToRelative(12f, 0f)
                curveToRelative(0.55f, 0f, 1f, -0.45f, 1f, -1f)
                lineToRelative(0f, -3.5f)
                lineToRelative(4f, 4f)
                lineToRelative(0f, -11f)
                lineToRelative(-4f, 4f)
                close()
            }
        }.build()
    }

    val VideocamOff: ImageVector by lazy {
        ImageVector.Builder("VideocamOff", 24.dp, 24.dp, 24f, 24f).apply {
            path(fill = SolidColor(Color.White)) {
                moveTo(21f, 6.5f)
                lineToRelative(-4f, 4f)
                lineTo(17f, 7f)
                curveToRelative(0f, -0.55f, -0.45f, -1f, -1f, -1f)
                lineTo(9.82f, 6f)
                lineTo(21f, 17.18f)
                lineTo(21f, 6.5f)
                close()
                moveTo(3.27f, 2f)
                lineTo(2f, 3.27f)
                lineTo(4.73f, 6f)
                lineTo(4f, 6f)
                curveToRelative(-0.55f, 0f, -1f, 0.45f, -1f, 1f)
                lineToRelative(0f, 10f)
                curveToRelative(0f, 0.55f, 0.45f, 1f, 1f, 1f)
                lineToRelative(12f, 0f)
                curveToRelative(0.21f, 0f, 0.39f, -0.08f, 0.54f, -0.18f)
                lineTo(19.73f, 21f)
                lineTo(21f, 19.73f)
                lineTo(3.27f, 2f)
                close()
                moveTo(5f, 16f)
                lineTo(5f, 8f)
                lineToRelative(1.27f, 0f)
                lineToRelative(8f, 8f)
                lineTo(5f, 16f)
                close()
            }
        }.build()
    }

    val Cameraswitch: ImageVector by lazy {
        ImageVector.Builder("Cameraswitch", 24.dp, 24.dp, 24f, 24f).apply {
            path(fill = SolidColor(Color.White)) {
                moveTo(16f, 7f)
                lineToRelative(-1f, 0f)
                lineToRelative(-1f, -1f)
                lineToRelative(-4f, 0f)
                lineToRelative(-1f, 1f)
                lineTo(8f, 7f)
                lineToRelative(0f, 2f)
                lineToRelative(8f, 0f)
                lineTo(16f, 7f)
                close()
                moveTo(8f, 11f)
                lineToRelative(0f, 6f)
                lineToRelative(4f, -2.5f)
                lineTo(8f, 11f)
                close()
                moveTo(16f, 11f)
                lineToRelative(-4f, 2.5f)
                lineToRelative(4f, 2.5f)
                lineTo(16f, 11f)
                close()
                moveTo(20f, 5f)
                lineToRelative(-3.17f, 0f)
                lineTo(15f, 3f)
                lineTo(9f, 3f)
                lineTo(7.17f, 5f)
                lineTo(4f, 5f)
                curveToRelative(-1.1f, 0f, -2f, 0.9f, -2f, 2f)
                lineToRelative(0f, 12f)
                curveToRelative(0f, 1.1f, 0.9f, 2f, 2f, 2f)
                lineToRelative(16f, 0f)
                curveToRelative(1.1f, 0f, 2f, -0.9f, 2f, -2f)
                lineTo(22f, 7f)
                curveToRelative(0f, -1.1f, -0.9f, -2f, -2f, -2f)
                close()
                moveTo(20f, 19f)
                lineTo(4f, 19f)
                lineTo(4f, 7f)
                lineToRelative(4.05f, 0f)
                lineToRelative(1.83f, -2f)
                lineToRelative(4.24f, 0f)
                lineToRelative(1.83f, 2f)
                lineTo(20f, 7f)
                lineToRelative(0f, 12f)
                close()
            }
        }.build()
    }

    val Palette: ImageVector by lazy {
        ImageVector.Builder("Palette", 24.dp, 24.dp, 24f, 24f).apply {
            path(fill = SolidColor(Color.White)) {
                moveTo(12f, 3f)
                curveToRelative(-4.97f, 0f, -9f, 4.03f, -9f, 9f)
                curveToRelative(0f, 2.12f, 0.74f, 4.07f, 1.97f, 5.61f)
                lineTo(4.35f, 19f)
                curveToRelative(-0.39f, 0.39f, -0.39f, 1.02f, 0f, 1.41f)
                curveToRelative(0.39f, 0.39f, 1.02f, 0.39f, 1.41f, 0f)
                lineToRelative(1.9f, -1.9f)
                curveTo(9.07f, 19.34f, 10.48f, 20f, 12f, 20f)
                curveToRelative(4.97f, 0f, 9f, -4.03f, 9f, -9f)
                reflectiveCurveToRelative(-4.03f, -9f, -9f, -9f)
                close()
                moveTo(6.5f, 12f)
                curveToRelative(-0.83f, 0f, -1.5f, -0.67f, -1.5f, -1.5f)
                reflectiveCurveTo(5.67f, 9f, 6.5f, 9f)
                reflectiveCurveTo(8f, 9.67f, 8f, 10.5f)
                reflectiveCurveTo(7.33f, 12f, 6.5f, 12f)
                close()
                moveTo(9.5f, 8f)
                curveTo(8.67f, 8f, 8f, 7.33f, 8f, 6.5f)
                reflectiveCurveTo(8.67f, 5f, 9.5f, 5f)
                reflectiveCurveToRelative(1.5f, 0.67f, 1.5f, 1.5f)
                reflectiveCurveTo(10.33f, 8f, 9.5f, 8f)
                close()
                moveTo(14.5f, 8f)
                curveToRelative(-0.83f, 0f, -1.5f, -0.67f, -1.5f, -1.5f)
                reflectiveCurveTo(13.67f, 5f, 14.5f, 5f)
                reflectiveCurveToRelative(1.5f, 0.67f, 1.5f, 1.5f)
                reflectiveCurveTo(15.33f, 8f, 14.5f, 8f)
                close()
                moveTo(17.5f, 12f)
                curveToRelative(-0.83f, 0f, -1.5f, -0.67f, -1.5f, -1.5f)
                reflectiveCurveTo(16.67f, 9f, 17.5f, 9f)
                reflectiveCurveToRelative(1.5f, 0.67f, 1.5f, 1.5f)
                reflectiveCurveToRelative(-0.67f, 1.5f, -1.5f, 1.5f)
                close()
            }
        }.build()
    }

    val NavigateNext: ImageVector by lazy {
        ImageVector.Builder("NavigateNext", 24.dp, 24.dp, 24f, 24f).apply {
            path(fill = SolidColor(Color.White)) {
                moveTo(10f, 6f)
                lineTo(8.59f, 7.41f)
                lineTo(13.17f, 12f)
                lineToRelative(-4.58f, 4.59f)
                lineTo(10f, 18f)
                lineToRelative(6f, -6f)
                close()
            }
        }.build()
    }

    val Psychology: ImageVector by lazy {
        ImageVector.Builder("Psychology", 24.dp, 24.dp, 24f, 24f).apply {
            path(fill = SolidColor(Color.White)) {
                moveTo(12f, 3f)
                curveToRelative(-4.97f, 0f, -9f, 4.03f, -9f, 9f)
                curveToRelative(0f, 2.12f, 0.74f, 4.07f, 1.97f, 5.61f)
                curveToRelative(0.38f, 0.48f, 0.53f, 1.1f, 0.41f, 1.7f)
                lineTo(5f, 21f)
                lineToRelative(4f, 0f)
                lineToRelative(0f, -2f)
                curveToRelative(0f, -0.55f, 0.45f, -1f, 1f, -1f)
                lineToRelative(1f, 0f)
                lineToRelative(0f, -2f)
                lineToRelative(2f, 0f)
                lineToRelative(0f, 2f)
                lineToRelative(1f, 0f)
                curveToRelative(0.55f, 0f, 1f, 0.45f, 1f, 1f)
                lineToRelative(0f, 2f)
                lineToRelative(4f, 0f)
                lineToRelative(-0.38f, -1.69f)
                curveToRelative(-0.12f, -0.6f, 0.03f, -1.22f, 0.41f, -1.7f)
                curveTo(20.26f, 16.07f, 21f, 14.12f, 21f, 12f)
                curveToRelative(0f, -4.97f, -4.03f, -9f, -9f, -9f)
                close()
                moveTo(11f, 8f)
                curveToRelative(0.83f, 0f, 1.5f, 0.67f, 1.5f, 1.5f)
                reflectiveCurveTo(11.83f, 11f, 11f, 11f)
                reflectiveCurveToRelative(-1.5f, -0.67f, -1.5f, -1.5f)
                reflectiveCurveTo(10.17f, 8f, 11f, 8f)
                close()
                moveTo(15.5f, 13.5f)
                curveToRelative(-0.83f, 0f, -1.5f, -0.67f, -1.5f, -1.5f)
                reflectiveCurveToRelative(0.67f, -1.5f, 1.5f, -1.5f)
                reflectiveCurveToRelative(1.5f, 0.67f, 1.5f, 1.5f)
                reflectiveCurveToRelative(-0.67f, 1.5f, -1.5f, 1.5f)
                close()
            }
        }.build()
    }

    val PushPin: ImageVector by lazy {
        ImageVector.Builder("PushPin", 24.dp, 24.dp, 24f, 24f).apply {
            path(fill = SolidColor(Color.White)) {
                moveTo(16f, 9f)
                lineTo(16f, 4f)
                lineToRelative(1f, 0f)
                curveToRelative(0.55f, 0f, 1f, -0.45f, 1f, -1f)
                reflectiveCurveToRelative(-0.45f, -1f, -1f, -1f)
                lineTo(7f, 2f)
                curveToRelative(-0.55f, 0f, -1f, 0.45f, -1f, 1f)
                reflectiveCurveToRelative(0.45f, 1f, 1f, 1f)
                lineToRelative(1f, 0f)
                lineToRelative(0f, 5f)
                curveToRelative(0f, 1.66f, -1.34f, 3f, -3f, 3f)
                lineToRelative(0f, 2f)
                lineToRelative(5.97f, 0f)
                lineToRelative(0f, 7f)
                lineToRelative(1f, 1f)
                lineToRelative(1f, -1f)
                lineToRelative(0f, -7f)
                lineTo(19f, 14f)
                lineToRelative(0f, -2f)
                curveToRelative(-1.66f, 0f, -3f, -1.34f, -3f, -3f)
                close()
            }
        }.build()
    }

    val Visibility: ImageVector by lazy {
        ImageVector.Builder("Visibility", 24.dp, 24.dp, 24f, 24f).apply {
            path(fill = SolidColor(Color.White)) {
                moveTo(12f, 4.5f)
                curveTo(7f, 4.5f, 2.73f, 7.61f, 1f, 12f)
                curveToRelative(1.73f, 4.39f, 6f, 7.5f, 11f, 7.5f)
                reflectiveCurveToRelative(9.27f, -3.11f, 11f, -7.5f)
                curveToRelative(-1.73f, -4.39f, -6f, -7.5f, -11f, -7.5f)
                close()
                moveTo(12f, 17f)
                curveToRelative(-2.76f, 0f, -5f, -2.24f, -5f, -5f)
                reflectiveCurveToRelative(2.24f, -5f, 5f, -5f)
                reflectiveCurveToRelative(5f, 2.24f, 5f, 5f)
                reflectiveCurveToRelative(-2.24f, 5f, -5f, 5f)
                close()
                moveTo(12f, 9f)
                curveToRelative(-1.66f, 0f, -3f, 1.34f, -3f, 3f)
                reflectiveCurveToRelative(1.34f, 3f, 3f, 3f)
                reflectiveCurveToRelative(3f, -1.34f, 3f, -3f)
                reflectiveCurveToRelative(-1.34f, -3f, -3f, -3f)
                close()
            }
        }.build()
    }

    val History: ImageVector by lazy {
        ImageVector.Builder("History", 24.dp, 24.dp, 24f, 24f).apply {
            path(fill = SolidColor(Color.White)) {
                moveTo(13f, 3f)
                curveToRelative(-4.97f, 0f, -9f, 4.03f, -9f, 9f)
                lineTo(1f, 12f)
                lineToRelative(3.89f, 3.89f)
                lineToRelative(0.07f, 0.14f)
                lineTo(9f, 12f)
                lineTo(6f, 12f)
                curveToRelative(0f, -3.87f, 3.13f, -7f, 7f, -7f)
                reflectiveCurveToRelative(7f, 3.13f, 7f, 7f)
                reflectiveCurveToRelative(-3.13f, 7f, -7f, 7f)
                curveToRelative(-1.93f, 0f, -3.68f, -0.79f, -4.94f, -2.06f)
                lineToRelative(-1.42f, 1.42f)
                curveTo(8.27f, 19.99f, 10.51f, 21f, 13f, 21f)
                curveToRelative(4.97f, 0f, 9f, -4.03f, 9f, -9f)
                reflectiveCurveToRelative(-4.03f, -9f, -9f, -9f)
                close()
                moveTo(12f, 8f)
                lineToRelative(0f, 5f)
                lineToRelative(4.28f, 2.54f)
                lineToRelative(0.72f, -1.21f)
                lineToRelative(-3.5f, -2.08f)
                lineTo(13.5f, 8f)
                lineTo(12f, 8f)
                close()
            }
        }.build()
    }

    val HistoryToggleOff: ImageVector by lazy {
        ImageVector.Builder("HistoryToggleOff", 24.dp, 24.dp, 24f, 24f).apply {
            path(fill = SolidColor(Color.White)) {
                moveTo(12f, 2f)
                curveTo(6.5f, 2f, 2f, 6.5f, 2f, 12f)
                reflectiveCurveToRelative(4.5f, 10f, 10f, 10f)
                reflectiveCurveToRelative(10f, -4.5f, 10f, -10f)
                reflectiveCurveTo(17.5f, 2f, 12f, 2f)
                close()
                moveTo(12f, 20f)
                curveToRelative(-4.41f, 0f, -8f, -3.59f, -8f, -8f)
                reflectiveCurveToRelative(3.59f, -8f, 8f, -8f)
                reflectiveCurveToRelative(8f, 3.59f, 8f, 8f)
                reflectiveCurveToRelative(-3.59f, 8f, -8f, 8f)
                close()
                moveTo(12.5f, 7f)
                lineTo(11f, 7f)
                lineToRelative(0f, 6f)
                lineToRelative(5.2f, 3.2f)
                lineToRelative(0.8f, -1.3f)
                lineToRelative(-4.5f, -2.7f)
                lineTo(12.5f, 7f)
                close()
            }
        }.build()
    }

    val DeleteOutline: ImageVector by lazy {
        ImageVector.Builder("DeleteOutline", 24.dp, 24.dp, 24f, 24f).apply {
            path(fill = SolidColor(Color.White)) {
                moveTo(6f, 19f)
                curveToRelative(0f, 1.1f, 0.9f, 2f, 2f, 2f)
                lineToRelative(8f, 0f)
                curveToRelative(1.1f, 0f, 2f, -0.9f, 2f, -2f)
                lineTo(18f, 7f)
                lineTo(6f, 7f)
                lineToRelative(0f, 12f)
                close()
                moveTo(8f, 9f)
                lineToRelative(8f, 0f)
                lineToRelative(0f, 10f)
                lineTo(8f, 19f)
                lineTo(8f, 9f)
                close()
                moveTo(15.5f, 4f)
                lineToRelative(-1f, -1f)
                lineToRelative(-5f, 0f)
                lineToRelative(-1f, 1f)
                lineTo(5f, 4f)
                lineToRelative(0f, 2f)
                lineToRelative(14f, 0f)
                lineTo(19f, 4f)
                lineToRelative(-3.5f, 0f)
                close()
            }
        }.build()
    }

    val CameraAlt: ImageVector by lazy {
        ImageVector.Builder("CameraAlt", 24.dp, 24.dp, 24f, 24f).apply {
            path(fill = SolidColor(Color.White)) {
                moveTo(20f, 4f)
                lineToRelative(-3.17f, 0f)
                lineTo(15f, 2f)
                lineTo(9f, 2f)
                lineTo(7.17f, 4f)
                lineTo(4f, 4f)
                curveToRelative(-1.1f, 0f, -2f, 0.9f, -2f, 2f)
                lineToRelative(0f, 12f)
                curveToRelative(0f, 1.1f, 0.9f, 2f, 2f, 2f)
                lineToRelative(16f, 0f)
                curveToRelative(1.1f, 0f, 2f, -0.9f, 2f, -2f)
                lineTo(22f, 6f)
                curveToRelative(0f, -1.1f, -0.9f, -2f, -2f, -2f)
                close()
                moveTo(20f, 18f)
                lineTo(4f, 18f)
                lineTo(4f, 6f)
                lineToRelative(4.05f, 0f)
                lineToRelative(1.83f, -2f)
                lineToRelative(4.24f, 0f)
                lineToRelative(1.83f, 2f)
                lineTo(20f, 6f)
                lineToRelative(0f, 12f)
                close()
                moveTo(12f, 7f)
                curveToRelative(-2.76f, 0f, -5f, 2.24f, -5f, 5f)
                reflectiveCurveToRelative(2.24f, 5f, 5f, 5f)
                reflectiveCurveToRelative(5f, -2.24f, 5f, -5f)
                reflectiveCurveToRelative(-2.24f, -5f, -5f, -5f)
                close()
                moveTo(12f, 15f)
                curveToRelative(-1.65f, 0f, -3f, -1.35f, -3f, -3f)
                reflectiveCurveToRelative(1.35f, -3f, 3f, -3f)
                reflectiveCurveToRelative(3f, 1.35f, 3f, 3f)
                reflectiveCurveToRelative(-1.35f, 3f, -3f, 3f)
                close()
            }
        }.build()
    }

    val TrendingUp: ImageVector by lazy {
        ImageVector.Builder("TrendingUp", 24.dp, 24.dp, 24f, 24f).apply {
            path(fill = SolidColor(Color.White)) {
                moveTo(16f, 6f)
                lineToRelative(2.29f, 2.29f)
                lineToRelative(-4.88f, 4.88f)
                lineToRelative(-4f, -4f)
                lineTo(2f, 16.59f)
                lineTo(3.41f, 18f)
                lineToRelative(6f, -6f)
                lineToRelative(4f, 4f)
                lineToRelative(6.3f, -6.29f)
                lineTo(22f, 12f)
                lineTo(22f, 6f)
                close()
            }
        }.build()
    }

    val Timer: ImageVector by lazy {
        ImageVector.Builder("Timer", 24.dp, 24.dp, 24f, 24f).apply {
            path(fill = SolidColor(Color.White)) {
                moveTo(15f, 1f)
                lineTo(9f, 1f)
                lineToRelative(0f, 2f)
                lineToRelative(6f, 0f)
                lineTo(15f, 1f)
                close()
                moveTo(11f, 14f)
                lineToRelative(2f, 0f)
                lineTo(13f, 8f)
                lineToRelative(-2f, 0f)
                lineToRelative(0f, 6f)
                close()
                moveTo(19.03f, 7.39f)
                lineToRelative(1.42f, -1.42f)
                curveToRelative(-0.43f, -0.51f, -0.9f, -0.99f, -1.41f, -1.41f)
                lineToRelative(-1.42f, 1.42f)
                curveTo(16.07f, 4.74f, 14.12f, 4f, 12f, 4f)
                curveToRelative(-4.97f, 0f, -9f, 4.03f, -9f, 9f)
                reflectiveCurveToRelative(4.02f, 9f, 9f, 9f)
                reflectiveCurveToRelative(9f, -4.03f, 9f, -9f)
                curveToRelative(0f, -2.12f, -0.74f, -4.07f, -1.97f, -5.61f)
                close()
                moveTo(12f, 20f)
                curveToRelative(-3.87f, 0f, -7f, -3.13f, -7f, -7f)
                reflectiveCurveToRelative(3.13f, -7f, 7f, -7f)
                reflectiveCurveToRelative(7f, 3.13f, 7f, 7f)
                reflectiveCurveToRelative(-3.13f, 7f, -7f, 7f)
                close()
            }
        }.build()
    }

    val ChatBubble: ImageVector by lazy {
        ImageVector.Builder("ChatBubble", 24.dp, 24.dp, 24f, 24f).apply {
            path(fill = SolidColor(Color.White)) {
                moveTo(20f, 2f)
                lineTo(4f, 2f)
                curveToRelative(-1.1f, 0f, -2f, 0.9f, -2f, 2f)
                lineToRelative(0f, 18f)
                lineToRelative(4f, -4f)
                lineToRelative(14f, 0f)
                curveToRelative(1.1f, 0f, 2f, -0.9f, 2f, -2f)
                lineTo(22f, 4f)
                curveToRelative(0f, -1.1f, -0.9f, -2f, -2f, -2f)
                close()
            }
        }.build()
    }

    val StarBorder: ImageVector by lazy {
        ImageVector.Builder("StarBorder", 24.dp, 24.dp, 24f, 24f).apply {
            path(fill = SolidColor(Color.White)) {
                moveTo(22f, 9.24f)
                lineToRelative(-7.19f, -0.62f)
                lineTo(12f, 2f)
                lineTo(9.19f, 8.63f)
                lineTo(2f, 9.24f)
                lineToRelative(5.46f, 4.73f)
                lineTo(5.82f, 21f)
                lineTo(12f, 17.27f)
                lineTo(18.18f, 21f)
                lineToRelative(-1.63f, -7.03f)
                lineTo(22f, 9.24f)
                close()
                moveTo(12f, 15.4f)
                lineToRelative(-3.76f, 2.27f)
                lineToRelative(1f, -4.28f)
                lineToRelative(-3.32f, -2.88f)
                lineToRelative(4.38f, -0.38f)
                lineTo(12f, 6.1f)
                lineToRelative(1.71f, 4.04f)
                lineToRelative(4.38f, 0.38f)
                lineToRelative(-3.32f, 2.88f)
                lineToRelative(1f, 4.28f)
                lineTo(12f, 15.4f)
                close()
            }
        }.build()
    }

    val Replay: ImageVector by lazy {
        ImageVector.Builder("Replay", 24.dp, 24.dp, 24f, 24f).apply {
            path(fill = SolidColor(Color.White)) {
                moveTo(12f, 5f)
                lineTo(12f, 1f)
                lineTo(7f, 6f)
                lineToRelative(5f, 5f)
                lineTo(12f, 7f)
                curveToRelative(3.31f, 0f, 6f, 2.69f, 6f, 6f)
                reflectiveCurveToRelative(-2.69f, 6f, -6f, 6f)
                reflectiveCurveToRelative(-6f, -2.69f, -6f, -6f)
                lineTo(4f, 13f)
                curveToRelative(0f, 4.42f, 3.58f, 8f, 8f, 8f)
                reflectiveCurveToRelative(8f, -3.58f, 8f, -8f)
                reflectiveCurveToRelative(-3.58f, -8f, -8f, -8f)
                close()
            }
        }.build()
    }
}
