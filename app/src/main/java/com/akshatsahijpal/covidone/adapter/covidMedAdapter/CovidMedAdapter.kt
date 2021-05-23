package com.akshatsahijpal.covidone.adapter.covidMedAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.akshatsahijpal.covidone.data.CovidData
import com.akshatsahijpal.covidone.databinding.DipItemBinding

class CovidMedAdapter: PagingDataAdapter<CovidData, CovidMedAdapter.Holder>(COMPARE)  {
    inner class Holder(private var _bind: DipItemBinding): RecyclerView.ViewHolder(_bind.root){
        fun bind(set: CovidData) {
            _bind.apply {
                AddressText .text = set.Address
                personName.text = set.Resource
                locationN.text = set.StateCity
            }
        }
    }
    companion object{
        private var COMPARE = object : DiffUtil.ItemCallback<CovidData>(){
            override fun areItemsTheSame(oldItem: CovidData, newItem: CovidData): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: CovidData, newItem: CovidData): Boolean {
                return oldItem == newItem
            }

            override fun getChangePayload(oldItem: CovidData, newItem: CovidData): Any? {
                return super.getChangePayload(oldItem, newItem)
            }
        }
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val currentPost = getItem(position)
        if (currentPost != null) {
            holder.bind(currentPost)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = DipItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(view)
    }

}