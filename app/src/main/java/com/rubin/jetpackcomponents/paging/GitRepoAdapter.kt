package com.rubin.jetpackcomponents.paging

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rubin.jetpackcomponents.R
import kotlinx.android.synthetic.main.list_item.view.*

class GitRepoAdapter : PagedListAdapter<GitRepo, GitRepoAdapter.RepoViewHolder>(REPO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return RepoViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val repo = getItem(position)
        repo?.let {
            holder.setData(repo)
        }
    }

    class RepoViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun setData(gitRepo: GitRepo) {
            view.repo_name.text = gitRepo.fullName
            view.repo_description.text = gitRepo.description
            view.repo_language.text = gitRepo.language
            view.repo_stars.text = gitRepo.stars.toString()
            view.repo_forks.text = gitRepo.forks.toString()
        }
    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<GitRepo>() {
            override fun areItemsTheSame(oldItem: GitRepo, newItem: GitRepo): Boolean =
                oldItem.fullName == newItem.fullName

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldGitItem: GitRepo, newGitItem: GitRepo): Boolean =
                oldGitItem == newGitItem
        }
    }
}