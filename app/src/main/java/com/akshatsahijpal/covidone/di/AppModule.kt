package com.akshatsahijpal.covidone.di

import android.content.Context
import androidx.room.Room
import com.akshatsahijpal.covidone.db.local.RunDAO
import com.akshatsahijpal.covidone.db.local.RunningDatabase
import com.akshatsahijpal.covidone.db.remote.unload.dataSource.FetchFireData
import com.akshatsahijpal.covidone.db.remote.upload.FirebaseUpload
import com.akshatsahijpal.covidone.repositories.uploadingRepo.LogsRepository
import com.akshatsahijpal.covidone.repositories.localRepo.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton
import com.google.firebase.firestore.FirebaseFirestore

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideRunningDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        RunningDatabase::class.java,
        "RUNNING_DATABASE_NAME"
    ).build()

    @Singleton
    @Provides
    fun provideRunDao(db: RunningDatabase) = db.getRunDao()

    @Singleton
    @Provides
    fun provideRepo(db: RunningDatabase, ins: FetchFireData) = MainRepository(db.getRunDao(), ins)

    @Singleton
    @Provides
    fun provideFirebaseUnloader(db: FirebaseFirestore, dao: RunDAO) = FetchFireData(db, dao)

    @Provides
    @Singleton
    fun provideFirebase() = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseUploader(db: FirebaseFirestore) = FirebaseUpload(db)

    @Provides
    @Singleton
    fun provideLogRepo(upl: FirebaseUpload) = LogsRepository(upl)

/*    @Provides
    @Singleton
    fun provideFetchFireData(db: FirebaseFirestore) = FetchFireData(db)*/
}