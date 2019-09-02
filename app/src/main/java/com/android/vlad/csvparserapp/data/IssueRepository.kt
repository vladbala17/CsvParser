package com.android.vlad.csvparserapp.data

import androidx.lifecycle.LiveData
import com.android.vlad.csvparserapp.data.source.local.IssueDAO
import com.android.vlad.csvparserapp.model.Issue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader

class IssueRepository(private val issueDAO: IssueDAO) {

    val allIssues: LiveData<List<Issue>> = issueDAO.getAllIssues()

    suspend fun refreshDb(path: InputStreamReader) {
        withContext(Dispatchers.IO) {
            issueDAO.deleteAllIssues()
            val result = parseCsv(path)
            for (issue in result) {
                issueDAO.insert(issue)
            }
        }
    }


    suspend fun parseCsv(path: InputStreamReader): List<Issue> = withContext(Dispatchers.IO) {
        val fileReader: BufferedReader?

        val issues = ArrayList<Issue>()
        var line: String?

        fileReader = BufferedReader(path)

        fileReader.readLine()

        line = fileReader.readLine()

        while (line != null) {
            val tokens = line.split(",")
            if (tokens.isNotEmpty()) {
                val issue = Issue(tokens[0], tokens[1], tokens[2], tokens[3])
                issues.add(issue)
            }
            line = fileReader.readLine()
        }
        return@withContext issues

    }

    suspend fun loadSortedIssues(): List<Issue> {
        return issueDAO.getIssuesSortedByName()
    }


}