package com.livepractice.simulator.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.livepractice.simulator.data.model.LiveComment
import com.livepractice.simulator.ui.theme.*

@Composable
fun LiveCommentItem(
    comment: LiveComment,
    isPinned: Boolean = false,
    onPinClick: () -> Unit = {}
) {
    val bubbleColor = when {
        isPinned -> PurpleAccent.copy(alpha = 0.85f)
        comment.isSuperChat -> GoldenBadge.copy(alpha = 0.35f)
        comment.isQuestion -> ElectricCyan.copy(alpha = 0.25f)
        else -> Color.Black.copy(alpha = 0.50f)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp),
        verticalAlignment = Alignment.Top
    ) {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(bubbleColor)
                .clickable { onPinClick() }
                .padding(horizontal = 10.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(22.dp)
                    .clip(CircleShape)
                    .background(Color(comment.avatarColorHex)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = comment.username.take(1).uppercase(),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp
                )
            }

            Spacer(modifier = Modifier.width(6.dp))

            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = comment.username,
                        color = if (comment.isSuperChat) GoldenBadge else TextSecondary,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp
                    )

                    if (comment.verified) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = "Verified",
                            tint = ElectricCyan,
                            modifier = Modifier.size(12.dp)
                        )
                    }

                    if (comment.superAmount != null) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(GoldenBadge)
                                .padding(horizontal = 4.dp, vertical = 1.dp)
                        ) {
                            Text(
                                text = comment.superAmount,
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                fontSize = 10.sp
                            )
                        }
                    }

                    if (comment.isQuestion) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(ElectricCyan)
                                .padding(horizontal = 4.dp, vertical = 1.dp)
                        ) {
                            Text(
                                text = "Q&A",
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                fontSize = 9.sp
                            )
                        }
                    }
                }

                Text(
                    text = comment.text,
                    color = TextPrimary,
                    fontSize = 13.sp,
                    lineHeight = 16.sp
                )
            }

            if (isPinned) {
                Spacer(modifier = Modifier.width(6.dp))
                Icon(
                    imageVector = AppIcons.PushPin,
                    contentDescription = "Pinned",
                    tint = NeonPink,
                    modifier = Modifier.size(14.dp)
                )
            }
        }
    }
}
