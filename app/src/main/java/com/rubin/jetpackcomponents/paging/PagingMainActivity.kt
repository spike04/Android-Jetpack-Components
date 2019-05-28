package com.rubin.jetpackcomponents.paging

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.rubin.jetpackcomponents.R
import kotlinx.android.synthetic.main.activity_paging_main.*

class PagingMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paging_main)

        val adapter = GitRepoAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this@PagingMainActivity)

        val itemViewModel = ViewModelProviders.of(this@PagingMainActivity).get(GitRepoViewModel::class.java)

        itemViewModel.gitRepoPagedList.observe(this@PagingMainActivity, Observer {
            adapter.submitList(it)
        })

        recyclerView.adapter = adapter
    }
}