package com.adamczajkowski.history

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.adamczajkowski.adapter.HistoryAdapter
import com.adamczajkowski.common.utils.BaseFragment
import com.adamczajkowski.feature.history_feature.databinding.FragmentHistoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : BaseFragment<FragmentHistoryBinding>(FragmentHistoryBinding::inflate) {

    private val viewModel: HistoryViewModel by viewModels()

    private val historyAdapter: HistoryAdapter by lazy {
        HistoryAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
        setRecycler()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getLastComparison()
    }

    private fun setRecycler() {
        with(binding.recycler) {
            layoutManager = LinearLayoutManager(requireContext())
            itemAnimator = null
            setHasFixedSize(true)
            adapter = historyAdapter
        }
    }

    private fun setObservers() {
        with(viewModel) {
            comparison.observe(viewLifecycleOwner) {
                historyAdapter.setItems(it)
            }
            errorMessage.observe(viewLifecycleOwner) {
                showErrorToast(it)
            }
        }
    }

    private fun showErrorToast(message: String?) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }
}