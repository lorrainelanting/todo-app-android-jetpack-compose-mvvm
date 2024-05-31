package com.lorrainedianne.memobly.core.app

import android.app.Application
import com.lorrainedianne.memobly.data.dataSource.db.AppRoomDatabase
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MemoblyApp: Application() {
    @Inject
    lateinit var localDatabase: AppRoomDatabase

    override fun onCreate() {
        super.onCreate()
        localDatabase = AppRoomDatabase.getInstance(this)
        localDatabase
    }
}