package com.akshatsahijpal.covidone.db.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.akshatsahijpal.covidone.data.CovidData

@Database(
    entities = [CovidData::class],
    version = 1
)
 abstract class RunningDatabase : RoomDatabase() {

    abstract fun getRunDao(): RunDAO
}