package com.adamczajkowski.feature.comparer_feature

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.adamczajkowski.common.models.Starship
import com.adamczajkowski.common.utils.BaseFragment
import com.adamczajkowski.feature.comparer_feature.databinding.FragmentTableBinding
import com.adamczajkowski.feature.comparer_feature.dialog.StarshipsDialog
import com.adamczajkowski.feature.comparer_feature.table.TableAdapter
import com.adamczajkowski.feature.comparer_feature.utils.SelectedVehicleAction
import com.adamczajkowski.feature.comparer_feature.viewModel.TableViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TableFragment : BaseFragment<FragmentTableBinding>(FragmentTableBinding::inflate),
    SelectedVehicleAction {

    private val viewModel: TableViewModel by viewModels()

    private val starshipsDialog: StarshipsDialog by lazy {
        StarshipsDialog.newInstance(viewModel.starships.value, this)
    }

    private val tableAdapter: TableAdapter by lazy {
        TableAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchStarships(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
        setOnClickListeners()
        setRecycler()
    }

    override fun selectedVehicle(starship: Starship) {
        viewModel.updateListOfStarships(starship)
        StarshipsDialog.getDialog(parentFragmentManager)?.updateTitle()
        updateCounter()
    }

    private fun setOnClickListeners() {
        binding.buttonSelectVehicles.setOnClickListener {
            starshipsDialog.show(parentFragmentManager, StarshipsDialog.STARSHIPS_DIALOG_TAG)
        }
    }

    private fun setObservers() {
        with(viewModel) {
            starships.observe(viewLifecycleOwner) {
                updateTableVisibility()
                tableAdapter.setStarships(
                    getSelectedVehicles(),
                    getComparedCategories()
                )
            }
            isLoading.observe(viewLifecycleOwner) {
                updateProgressBarVisibility(it)
            }
            errorMessage.observe(viewLifecycleOwner) {
                showErrorToast()
            }
        }
    }

    private fun setRecycler() {
        with(binding) {
            with(recyclerTable) {
                layoutManager = LinearLayoutManager(requireContext()).apply {
                    orientation = LinearLayoutManager.HORIZONTAL
                }
                itemAnimator = null
                adapter = tableAdapter
            }
        }
    }

    private fun updateProgressBarVisibility(isVisible: Boolean) {
        binding.progressBar.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    private fun updateTableVisibility() {
        if (viewModel.getSelectedVehicles().isNotEmpty()) {
            binding.tableGroup.visibility = View.VISIBLE
            binding.instructionText.visibility = View.INVISIBLE
        } else {
            binding.instructionText.visibility = View.VISIBLE
            binding.tableGroup.visibility = View.INVISIBLE
        }
        binding.container.invalidate()
    }

    private fun showErrorToast() {
        Toast.makeText(
            requireContext(),
            requireContext().getString(com.adamczajkowski.common.R.string.error_message),
            Toast.LENGTH_LONG
        ).show()
    }

    private fun updateCounter() {
        binding.selectedCounter.text = requireContext().getString(
            com.adamczajkowski.common.R.string.counter_text,
            viewModel.getSelectedCount()
        )
    }

    override fun onDestroyView() {
        viewModel.onDestroyView()
        super.onDestroyView()
    }
}