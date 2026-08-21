package com.livepractice.simulator

import android.app.Application
import androidx.room.Room
import com.livepractice.simulator.data.db.AppDatabase

class LivePracticeApp : Application() {
    lateinit var database: AppDatabase
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "live_practice.db"
        ).fallbackToDestructiveMigration().build()
    }

    companion object {
        lateinit var instance: LivePracticeApp
            private set
    }
}
