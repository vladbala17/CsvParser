package com.android.vlad.csvparserapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.vlad.csvparserapp.R
import com.android.vlad.csvparserapp.model.Issue

class IssueAdapter internal constructor(context: Context) : RecyclerView.Adapter<IssueAdapter.IssueViewHolder>() {

    val inflater: LayoutInflater = LayoutInflater.from(context)
    private var issues = emptyList<Issue>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IssueViewHolder {
        val itemView = inflater.inflate(R.layout.list_item_layout, parent, false)
        return IssueViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return issues.size
    }

    override fun onBindViewHolder(holder: IssueViewHolder, position: Int) {
        val current = issues[position]
        holder.firstName.text = current.firstName.removeSurrounding("\"")
        holder.surName.text = current.surName.removeSurrounding("\"")
        holder.issueCount.text = current.issue.removeSurrounding("\"")
        holder.dateOfBirth.text = current.dateOfBirth.removeSurrounding("\"")
    }

    inner class IssueViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val firstName: TextView = itemView.findViewById(R.id.first_name_txt)
        val surName: TextView = itemView.findViewById(R.id.sur_name_txt)
        val issueCount: TextView = itemView.findViewById(R.id.issue_count_txt)
        val dateOfBirth: TextView = itemView.findViewById(R.id.date_of_birth_txt)
    }

    internal fun setIssues(issues: List<Issue>) {
        this.issues = issues
        notifyDataSetChanged()
    }
}