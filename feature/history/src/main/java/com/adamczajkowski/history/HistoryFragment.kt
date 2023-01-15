package com.adamczajkowski.history

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.adamczajkowski.common.utils.BaseFragment
import com.adamczajkowski.feature.history_feature.databinding.FragmentHistoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : BaseFragment<FragmentHistoryBinding>(FragmentHistoryBinding::inflate) {

    private val viewModel: HistoryViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
    }

    private fun setObservers() {
        viewModel.text.observe(viewLifecycleOwner) {
            binding.textDashboard.text = it
        }
    }
}