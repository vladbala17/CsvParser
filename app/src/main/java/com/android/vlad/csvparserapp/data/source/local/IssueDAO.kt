package com.android.vlad.csvparserapp.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.android.vlad.csvparserapp.model.Issue

@Dao
interface IssueDAO {

    @Insert
    suspend fun insert(issue: Issue)

    @Query("SELECT * FROM issue_table")
    fun getAllIssues(): LiveData<List<Issue>>

    @Query("DELETE FROM issue_table")
    fun deleteAllIssues()

    @Query("SELECT * FROM issue_table ORDER BY `First name` ASC")
    suspend fun getIssuesSortedByName(): List<Issue>
}