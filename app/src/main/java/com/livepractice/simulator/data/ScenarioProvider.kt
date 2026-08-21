package com.livepractice.simulator.data

import com.livepractice.simulator.data.model.LiveScenario

object ScenarioProvider {
    val defaultScenarios: List<LiveScenario> = listOf(
        LiveScenario(
            id = "ama_casual",
            title = "Ask Me Anything (AMA)",
            description = "Casual community stream answering spontaneous follower questions and building connection.",
            category = "Social & Community",
            icon = "💬",
            baseViewers = 1850,
            commentPaceMs = 1800L,
            sampleComments = listOf(
                "Hey everyone! Happy to be here 🎉",
                "Where are you streaming from today?",
                "What inspired you to start doing this?",
                "Can you shout out @marcus in the chat?",
                "Love your setup and lighting!",
                "What is your #1 productivity tip?",
                "How do you deal with creative burnout?",
                "Big fan from Toronto! 🇨🇦",
                "Dropped a like! Keep it up!",
                "Are you planning any meetups soon?",
                "What was the hardest obstacle you overcame?",
                "Any book recommendations for this month?",
                "Sending good vibes from Tokyo! 🗼",
                "Your audio is super crisp today!",
                "What's your favorite project so far?"
            ),
            promptChallenges = listOf(
                "💡 A viewer asked for advice—give a concise 30-second answer!",
                "👏 Give a quick shoutout to your community and thank them for joining!",
                "🌍 Ask the chat where they're tuning in from and wait for responses!",
                "🎯 Share one surprising fact about yourself that most people don't know!"
            ),
            difficulty = "Beginner"
        ),
        LiveScenario(
            id = "keynote_pitch",
            title = "Tech Keynote & Pitch",
            description = "High-stakes product announcement, pitch deck rehearsal, and addressing stakeholder inquiries.",
            category = "Professional Speech",
            icon = "🚀",
            baseViewers = 8400,
            commentPaceMs = 1400L,
            sampleComments = listOf(
                "Is the API documentation available yet?",
                "How does this scale with high concurrent traffic?",
                "What's the pricing tier for small startups?",
                "The UI architecture looks very clean!",
                "Will there be native Kotlin & Compose SDKs?",
                "Great cadence and pacing on this slide.",
                "How does this compare to incumbent solutions?",
                "Watching with my engineering team!",
                "Very compelling market opportunity slide.",
                "Security compliance details?",
                "Timeline for general availability?",
                "Impressive live demo, zero latency!"
            ),
            promptChallenges = listOf(
                "🎤 Deliver your core elevator pitch in under 45 seconds!",
                "🛡️ Address a tough architecture question from the audience with confidence!",
                "📊 Summarize your competitive advantage in 3 clear bullet points!",
                "🚀 Transition smoothly to your call-to-action conclusion!"
            ),
            difficulty = "Intermediate"
        ),
        LiveScenario(
            id = "fitness_coaching",
            title = "Fitness & Wellness Coach",
            description = "Interactive fitness coaching, guiding form, energy motivation, and hydration checks.",
            category = "Coaching & Energy",
            icon = "⚡",
            baseViewers = 3600,
            commentPaceMs = 1600L,
            sampleComments = listOf(
                "Let's goooo! Ready for today's session 🔥",
                "Feeling the burn already on set 2!",
                "What should I stretch before bed?",
                "Best pre-workout snack?",
                "Posture check reminder needed 😂",
                "My heart rate is at 145 bpm!",
                "Can you show a low-impact variation?",
                "Drinking water right now 💧",
                "Day 14 streak thanks to these lives!",
                "Awesome motivational energy!",
                "Form cues are super helpful, thank you!"
            ),
            promptChallenges = listOf(
                "💧 Call for a mandatory 15-second hydration check!",
                "🔥 Give a high-energy motivational push for the final stretch!",
                "🧘 Guide viewers through a 3-count inhale and exhale reset!",
                "🙌 Acknowledge viewers on a daily streak in the chat!"
            ),
            difficulty = "Beginner"
        ),
        LiveScenario(
            id = "pr_crisis",
            title = "Crisis PR & Press Briefing",
            description = "High-pressure press statement under intense scrutiny. Master calm tone, clarity, and firm control.",
            category = "Crisis Communications",
            icon = "🎙️",
            baseViewers = 16500,
            commentPaceMs = 900L,
            sampleComments = listOf(
                "Why was there no advance notice?",
                "What steps are being taken right now?",
                "Can you clarify the exact timeline of events?",
                "Will affected users be compensated?",
                "Who authorized the initial change?",
                "Appreciate the prompt live briefing.",
                "How will you prevent this from recurring?",
                "We need transparent audit logs.",
                "Clear response on the first inquiry.",
                "When is the full post-mortem being published?"
            ),
            promptChallenges = listOf(
                "🛡️ Maintain steady, calm eye contact while delivering the opening statement!",
                "⚖️ Avoid defensive language—acknowledge the concern with empathy!",
                "🔍 Clearly outline the 3 concrete remedial actions being implemented!",
                "🛑 Set clear boundaries and redirect back to verified facts!"
            ),
            difficulty = "High Pressure"
        ),
        LiveScenario(
            id = "gaming_hype",
            title = "Gaming & Hype Stream",
            description = "Fast-paced, vibrant entertainment streaming with gifts, hype trains, and rapid chat reactions.",
            category = "Entertainment & Gaming",
            icon = "🎮",
            baseViewers = 6200,
            commentPaceMs = 1000L,
            sampleComments = listOf(
                "POGGERS IN THE CHAT 🔥🔥",
                "LMAOO that clutch play!",
                "Hype train level 3 unlocked 🚂💨",
                "GG WP! That was insane!",
                "Drop the custom crosshair settings!",
                "Send in the galaxy gifts 🌌✨",
                "10/10 reaction timing!",
                "SPAM 🚀 IF WE WIN THIS",
                "Gifted 5 subs to the stream!",
                "One more round please! Don't end yet!"
            ),
            promptChallenges = listOf(
                "🎉 React to a sudden simulated 100-gift burst with hype!",
                "🚀 Ask the chat to spam an emoji if they want an overtime round!",
                "🏆 Thank your top community moderator @pixel_queen!",
                "💥 Celebrate a big milestone with genuine excitement!"
            ),
            difficulty = "Intermediate"
        )
    )

    val randomUsernames = listOf(
        "cyber_samurai", "luna_starlight", "alex_dev99", "pixel_master",
        "maya_spark", "neon_rider", "jordan_flows", "zen_coder",
        "elena_creative", "vibe_chaser", "kai_streams", "hannah_speaks",
        "crypto_hawk", "sunny_days", "leo_fitness", "chloe_vlogs",
        "marcus_prime", "clara_music", "dan_runner", "zara_glow",
        "epic_gamer42", "tina_designs", "kevin_explores", "sophie_art"
    )

    val randomAvatars = listOf(
        0xFFFF2A6D, 0xFF00F0FF, 0xFFFFE600, 0xFF9D4EDD,
        0xFF00E676, 0xFFFF6B6B, 0xFF4D96FF, 0xFFFFB703,
        0xFFE056FD, 0xFF22A6B3, 0xFF6C5CE7, 0xFFFFA801
    )

    val simulatedGifts = listOf(
        Triple("Sparkle Rose", "🌹", 10),
        Triple("Energy Drink", "⚡", 50),
        Triple("Diamond Crown", "👑", 200),
        Triple("Rocket Boost", "🚀", 500),
        Triple("Galaxy Portal", "🌌", 1000)
    )
}
