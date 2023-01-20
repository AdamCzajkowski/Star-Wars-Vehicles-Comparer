package com.adamczajkowski.feature.comparer_feature.dialog

import androidx.recyclerview.widget.RecyclerView
import com.adamczajkowski.common.models.Starship
import com.adamczajkowski.feature.comparer_feature.databinding.StarshipDialogRecyclerItemBinding
import com.adamczajkowski.feature.comparer_feature.utils.SelectedVehicleAction

class StarshipViewHolder(
    private val viewBinding: StarshipDialogRecyclerItemBinding,
    private val callback: SelectedVehicleAction
) : RecyclerView.ViewHolder(viewBinding.root) {

    fun bind(starship: Starship) {
        with(viewBinding) {
            starshipDialogRecyclerItemName.text = starship.name
            starshipDialogRecyclerItemRadioButton.isChecked = starship.isSelected
            starshipDialogRecyclerItemContainer.setOnClickListener {
                starshipDialogRecyclerItemRadioButton.isChecked =
                    !starshipDialogRecyclerItemRadioButton.isChecked
            }
            starshipDialogRecyclerItemRadioButton.isClickable = false
            starshipDialogRecyclerItemRadioButton.setOnCheckedChangeListener { _, _ ->
                starship.isSelected = starshipDialogRecyclerItemRadioButton.isChecked
                callback.selectedVehicle(starship)
            }
        }
    }
}