package com.adamczajkowski.feature.comparer_feature.table

import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.adamczajkowski.common.R
import com.adamczajkowski.common.models.Starship
import com.adamczajkowski.feature.comparer_feature.databinding.TableItemBinding
import com.adamczajkowski.feature.comparer_feature.model.CategoriesCombine
import com.adamczajkowski.feature.comparer_feature.model.ComparedCategory

class TableViewHolder(
    private val viewBinding: TableItemBinding
) : RecyclerView.ViewHolder(viewBinding.root) {

    fun bind(starship: Starship, combine: CategoriesCombine?) {
        setStarshipDetails(starship)
        setWorstAndBestValues(starship, combine)
    }

    private fun setStarshipDetails(starship: Starship) {
        with(viewBinding) {
            starshipName.text = starship.name
            firstCategory.text = starship.costInCredits
            secondCategory.text = starship.length
            thirdCategory.text = starship.maxAtmospheringSpeed
            fourthCategory.text = starship.crew
            fifthCategory.text = starship.passengers
            sixthCategory.text = starship.cargoCapacity
            seventhCategory.text = starship.consumables
            eighthCategory.text = starship.hyperdriveRating
            ninthCategory.text = starship.mglt
        }
    }

    private fun setWorstAndBestValues(starship: Starship, combine: CategoriesCombine?) {
        if (combine != null) {
            with(viewBinding) {
                firstCategory.setHighlights(starship.costInCredits, combine.costInCredit, true)
                secondCategory.setHighlights(starship.length, combine.length)
                thirdCategory.setHighlights(
                    starship.maxAtmospheringSpeed,
                    combine.maxAtmospheringSpeed
                )
                fourthCategory.setHighlights(starship.crew, combine.crew)
                fifthCategory.setHighlights(starship.passengers, combine.passengers)
                sixthCategory.setHighlights(starship.cargoCapacity, combine.cargoCapacity)
                seventhCategory.setHighlights(starship.consumables, combine.consumables)
                eighthCategory.setHighlights(starship.hyperdriveRating, combine.hyperdriveRating)
                ninthCategory.setHighlights(starship.mglt, combine.mglt)
            }
        }
    }

    private fun TextView.setHighlights(
        value: String?,
        comparedCategory: ComparedCategory,
        isReversedValue: Boolean = false
    ) {
        when (value?.isInHighlightedValue(comparedCategory)) {
            Pair(true, true), Pair(false, false) -> background =
                ContextCompat.getDrawable(
                    context,
                    R.drawable.background_category_separator
                )
            Pair(true, false) -> background =
                ContextCompat.getDrawable(
                    context,
                    if (isReversedValue) R.drawable.background_highlight_best else R.drawable.background_highlight_worst
                )
            Pair(false, true) -> background =
                ContextCompat.getDrawable(
                    context,
                    if (isReversedValue) R.drawable.background_highlight_worst else R.drawable.background_highlight_best
                )
        }
    }

    private fun String.isInHighlightedValue(comparedCategory: ComparedCategory): Pair<Boolean, Boolean> {
        return Pair(this == comparedCategory.lowestValue, this == comparedCategory.highestValue)
    }
}