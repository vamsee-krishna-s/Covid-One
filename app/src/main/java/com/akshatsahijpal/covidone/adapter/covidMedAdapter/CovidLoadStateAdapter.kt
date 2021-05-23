package com.akshatsahijpal.covidone.adapter.covidMedAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.akshatsahijpal.covidone.databinding.HeaderFootorLoaderBinding

class CovidLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<CovidLoadStateAdapter.Holder>() {
    override fun onBindViewHolder(holder: Holder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): Holder {
        val view =
            HeaderFootorLoaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(view)
    }

    inner class Holder(private var item: HeaderFootorLoaderBinding) :
        RecyclerView.ViewHolder(item.root) {
        init {
            item.button404.setOnClickListener {
                 retry.invoke()

            }
        }

        fun bind(loadState: LoadState) {
            item.apply {
                progressBar404.isVisible = loadState is LoadState.Loading
                button404.isVisible = loadState !is LoadState.Loading
                textView404.isVisible = loadState !is LoadState.Loading
            }
        }
    }
}