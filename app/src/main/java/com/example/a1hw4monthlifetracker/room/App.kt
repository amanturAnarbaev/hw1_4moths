package com.example.a1hw4monthlifetracker.room

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase

class App :Application () {
    companion object{
        lateinit var appDataBase: AppDataBase
    }

    override fun onCreate() {
        super.onCreate()
        appDataBase= Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java, "database-name"
        ).allowMainThreadQueries().build()
    }
}