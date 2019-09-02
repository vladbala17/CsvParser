package com.android.vlad.csvparserapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.vlad.csvparserapp.ui.IssueAdapter

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var issueViewModel: IssueViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.issue_rv)
        val adapter = IssueAdapter(this)
        recyclerView.adapter = adapter

        recyclerView.layoutManager = LinearLayoutManager(this)

        val button = findViewById<Button>(R.id.sort_btn)
        button.setOnClickListener(this)
        issueViewModel = ViewModelProviders.of(this).get(IssueViewModel::class.java)

        issueViewModel.refreshDb()

        issueViewModel.allIssues.observe(
            this,
            Observer { issue -> issue?.let { adapter.setIssues(issue) } })

        issueViewModel.sortedIssues.observe(
            this,
            Observer { issue -> issue?.let { adapter.setIssues(issue) } })

    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.sort_btn -> {
                issueViewModel.onSortByFirstName()
            }
        }
    }


}
