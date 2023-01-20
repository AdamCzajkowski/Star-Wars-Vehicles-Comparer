package com.adamczajkowski.feature.comparer_feature.dialog

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.adamczajkowski.common.R.string
import com.adamczajkowski.common.R.style
import com.adamczajkowski.common.models.Starship
import com.adamczajkowski.common.utils.BaseBottomSheetDialogFragment
import com.adamczajkowski.common.utils.parcelableArrayList
import com.adamczajkowski.common.utils.withArguments
import com.adamczajkowski.feature.comparer_feature.databinding.DialogStarshipsBinding
import com.adamczajkowski.feature.comparer_feature.utils.SelectedVehicleAction

class StarshipsDialog(
    private val callback: SelectedVehicleAction
) : BaseBottomSheetDialogFragment<DialogStarshipsBinding>(DialogStarshipsBinding::inflate) {

    private val starshipsAdapter: StarshipsAdapter by lazy {
        StarshipsAdapter(callback)
    }

    private var starships: List<Starship>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(
            DialogFragment.STYLE_NO_TITLE,
            style.Theme_StarWarsVehiclesComparer_BottomSheetRounded
        )

        starships = arguments?.parcelableArrayList<Starship>(STARSHIPS_KEY)
            ?: throw java.lang.IllegalArgumentException("missing argument")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setView()
        setData()
    }

    override fun onDismiss(dialog: DialogInterface) {
        callback.onDialogDismiss()
        super.onDismiss(dialog)
    }

    private fun setView() {
        with(binding.starshipsDialogRecycler) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            itemAnimator = null
            adapter = starshipsAdapter
        }
        updateTitle()
        binding.starshipDialogConfirmButton.setOnClickListener {
            dismiss()
        }
    }

    fun updateTitle() {
        val selectedVehiclesNumber = starships?.filter { it.isSelected }?.size
        if (selectedVehiclesNumber != null) {
            binding.starshipsDialogLabelText.text = if (selectedVehiclesNumber > 1)
                context?.getString(
                    string.starship_dialog_title_multiple,
                    selectedVehiclesNumber
                ) else if (selectedVehiclesNumber == 1)
                context?.getString(string.starship_dialog_title_single)
            else
                context?.getString(string.text_button_select_vehicles)
        }
    }

    private fun setData() {
        starships?.let { starshipsAdapter.setStarships(it) }
    }

    companion object {
        const val STARSHIPS_DIALOG_TAG = "STARSHIPS_DIALOG_TAG"
        private const val STARSHIPS_KEY = "STARSHIPS_KEY"

        fun getDialog(parentFragmentManager: FragmentManager): StarshipsDialog? {
            return parentFragmentManager.findFragmentByTag(STARSHIPS_DIALOG_TAG) as? StarshipsDialog
        }

        fun newInstance(
            starships: List<Starship>?,
            callback: SelectedVehicleAction
        ): StarshipsDialog {
            if (starships != null) {
                return StarshipsDialog(callback)
                    .withArguments(STARSHIPS_KEY to starships)
            } else throw IllegalArgumentException("missing starships")
        }
    }
}