package com.adamczajkowski.feature.comparer_feature.dialog

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adamczajkowski.common.models.Starship
import com.adamczajkowski.feature.comparer_feature.databinding.StarshipDialogRecyclerItemBinding
import com.adamczajkowski.feature.comparer_feature.utils.SelectedVehicleAction

class StarshipsAdapter(
    private val callback: SelectedVehicleAction
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items = listOf<Starship>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return StarshipViewHolder(
            StarshipDialogRecyclerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), callback
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as StarshipViewHolder).bind(items[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setStarships(list :List<Starship>) {
        items = emptyList()
        items = list
        notifyDataSetChanged()
    }
}