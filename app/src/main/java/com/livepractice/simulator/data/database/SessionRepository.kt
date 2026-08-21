package com.livepractice.simulator.data.database

import kotlinx.coroutines.flow.Flow

class SessionRepository(private val sessionDao: SessionDao) {
    val allSessions: Flow<List<SessionLog>> = sessionDao.getAllSessions()
    val sessionCount: Flow<Int> = sessionDao.getSessionCount()
    val totalPracticeSeconds: Flow<Long?> = sessionDao.getTotalPracticeSeconds()

    suspend fun insertSession(session: SessionLog): Long {
        return sessionDao.insertSession(session)
    }

    suspend fun deleteSession(session: SessionLog) {
        sessionDao.deleteSession(session)
    }

    suspend fun deleteSessionById(id: Long) {
        sessionDao.deleteSessionById(id)
    }

    suspend fun getSessionById(id: Long): SessionLog? {
        return sessionDao.getSessionById(id)
    }
}
