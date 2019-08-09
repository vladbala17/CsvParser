package com.android.vlad.csvparserapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.android.vlad.csvparserapp.data.IssueRepository
import com.android.vlad.csvparserapp.data.source.local.IssueDatabase
import com.android.vlad.csvparserapp.model.Issue
import kotlinx.coroutines.launch
import java.io.InputStreamReader
import java.nio.charset.Charset


class IssueViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: IssueRepository
    private val filePath: InputStreamReader
    val allIssues: LiveData<List<Issue>>

    init {
        val issueDAO = IssueDatabase.getDatabase(application.applicationContext).issueDao()
        repository = IssueRepository(issueDAO)
        filePath = application.assets.open("issues.csv").reader(Charset.defaultCharset())
        allIssues = repository.allIssues
    }

    fun refreshDb() {
        viewModelScope.launch {
            repository.refreshDb(filePath)
        }
    }
}