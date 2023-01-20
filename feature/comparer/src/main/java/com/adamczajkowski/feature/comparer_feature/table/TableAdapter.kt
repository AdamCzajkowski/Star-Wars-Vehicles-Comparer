package com.adamczajkowski.feature.comparer_feature.table

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adamczajkowski.common.models.Starship
import com.adamczajkowski.feature.comparer_feature.databinding.TableItemBinding
import com.adamczajkowski.feature.comparer_feature.model.CategoriesCombine

class TableAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items = listOf<Starship>()

    private var categoriesCombine: CategoriesCombine? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TableViewHolder(
            TableItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as TableViewHolder).bind(items[position], categoriesCombine)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setStarships(list: List<Starship>, combine: CategoriesCombine) {
        categoriesCombine = combine
        items = emptyList()
        items = list
        notifyDataSetChanged()
    }
}