package com.android.vlad.csvparserapp.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.vlad.csvparserapp.model.Issue

@Database(entities = [Issue::class], version = 1)
abstract class IssueDatabase : RoomDatabase() {

    abstract fun issueDao(): IssueDAO

    companion object {

        @Volatile
        private var INSTANCE: IssueDatabase? = null

        fun getDatabase(context: Context): IssueDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance =
                    Room.databaseBuilder(context.applicationContext, IssueDatabase::class.java, "Issue_Database")
                        .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}