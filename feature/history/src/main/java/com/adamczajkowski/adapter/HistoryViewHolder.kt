package com.adamczajkowski.adapter

import androidx.recyclerview.widget.RecyclerView
import com.adamczajkowski.common.models.StarshipComparisonHistoryItem
import com.adamczajkowski.feature.history_feature.databinding.HistoryItemBinding
import java.text.SimpleDateFormat
import java.util.*

class HistoryViewHolder(
    private val viewBinding: HistoryItemBinding
) : RecyclerView.ViewHolder(viewBinding.root) {

    companion object {
        private const val DATE_PATTERN = "dd/MM/yy HH:mm:ss"
        private const val CLOSE_BRACKET = "]"
        private const val OPEN_BRACKET = "["
    }

    private val dateFormat = SimpleDateFormat(DATE_PATTERN, Locale.getDefault())

    fun bind(comparison: StarshipComparisonHistoryItem) {
        with(viewBinding) {
            vehicles.text = comparison
                .vehicles
                .substringAfter(OPEN_BRACKET)
                .substringBefore(CLOSE_BRACKET)

            date.text = comparison.addedDate?.let { dateFormat.format(it) }
        }
    }
}