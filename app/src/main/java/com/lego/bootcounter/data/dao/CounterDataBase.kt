package com.lego.bootcounter.data.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.lego.bootcounter.data.models.Counter

@Database(entities = [Counter::class], version = 1)
abstract class CounterDataBase : RoomDatabase() {

    abstract fun counterDao(): CounterDao

    companion object {

        private const val DATA_BASE_NAME = "COUNTER_DATABASE"

        // For Singleton instantiation
        @Volatile
        private var instance: CounterDataBase? = null

        fun getInstance(context: Context): CounterDataBase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }


        private fun buildDatabase(context: Context): CounterDataBase {
            return Room
                .databaseBuilder(context, CounterDataBase::class.java, DATA_BASE_NAME)
                .addCallback(
                    object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            val request = OneTimeWorkRequestBuilder<DatabaseWorker>()
                                .build()
                            WorkManager.getInstance(context).enqueue(request)
                        }
                    }
                )
                .build()
        }
    }

}