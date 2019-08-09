package com.android.vlad.csvparserapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.vlad.csvparserapp.ui.IssueAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var issueViewModel: IssueViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.issue_rv)
        val adapter = IssueAdapter(this)
        recyclerView.adapter = adapter

        recyclerView.layoutManager = LinearLayoutManager(this)

        issueViewModel = ViewModelProviders.of(this).get(IssueViewModel::class.java)

        issueViewModel.refreshDb()

        issueViewModel.allIssues.observe(this, Observer { issue -> issue?.let { adapter.setIssues(issue) } })

    }
}
