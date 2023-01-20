package com.adamczajkowski.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adamczajkowski.common.models.StarshipComparisonHistoryItem
import com.adamczajkowski.feature.history_feature.databinding.HistoryItemBinding

class HistoryAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items = listOf<StarshipComparisonHistoryItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HistoryViewHolder(
            HistoryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as HistoryViewHolder).bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(list: List<StarshipComparisonHistoryItem>) {
        items = emptyList()
        items = list
        notifyDataSetChanged()
    }
}