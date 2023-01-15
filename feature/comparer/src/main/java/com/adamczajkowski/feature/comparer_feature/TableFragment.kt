package com.adamczajkowski.feature.comparer_feature

import android.os.Bundle
import android.view.View
import com.adamczajkowski.common.utils.BaseFragment
import com.adamczajkowski.feature.comparer_feature.databinding.FragmentTableBinding
import dagger.hilt.android.AndroidEntryPoint
import androidx.fragment.app.viewModels

@AndroidEntryPoint
class TableFragment : BaseFragment<FragmentTableBinding>(FragmentTableBinding::inflate) {

    private val viewModel: TableViewModel by viewModels()

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