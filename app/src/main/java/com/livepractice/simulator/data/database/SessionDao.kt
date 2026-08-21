package com.livepractice.simulator.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SessionDao {
    @Query("SELECT * FROM session_logs ORDER BY dateTimestamp DESC")
    fun getAllSessions(): Flow<List<SessionLog>>

    @Query("SELECT * FROM session_logs WHERE id = :id LIMIT 1")
    suspend fun getSessionById(id: Long): SessionLog?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSession(session: SessionLog): Long

    @Delete
    suspend fun deleteSession(session: SessionLog)

    @Query("DELETE FROM session_logs WHERE id = :id")
    suspend fun deleteSessionById(id: Long)

    @Query("SELECT COUNT(*) FROM session_logs")
    fun getSessionCount(): Flow<Int>

    @Query("SELECT SUM(durationSeconds) FROM session_logs")
    fun getTotalPracticeSeconds(): Flow<Long?>
}
