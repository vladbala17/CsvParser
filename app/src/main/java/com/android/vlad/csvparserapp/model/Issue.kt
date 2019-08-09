package com.android.vlad.csvparserapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "issue_table")
data class Issue(
    @ColumnInfo(name = "First name") var firstName: String,
    @ColumnInfo(name = "Sur name") var surName: String,
    @ColumnInfo(name = "Issue count") var issue: String,
    @ColumnInfo(name = "Date of birth") var dateOfBirth: String) {

    @PrimaryKey(autoGenerate = true) var id: Int = 0
}

