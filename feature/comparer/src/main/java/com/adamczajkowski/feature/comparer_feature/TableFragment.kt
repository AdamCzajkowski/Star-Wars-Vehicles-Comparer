package com.adamczajkowski.feature.comparer_feature

import android.os.Bundle
import android.view.View
import com.adamczajkowski.common.utils.BaseFragment
import com.adamczajkowski.feature.comparer_feature.databinding.FragmentTableBinding
import dagger.hilt.android.AndroidEntryPoint
import androidx.fragment.app.viewModels
import com.adamczajkowski.feature.comparer_feature.viewModel.TableViewModel

@AndroidEntryPoint
class TableFragment : BaseFragment<FragmentTableBinding>(FragmentTableBinding::inflate) {

    private val viewModel: TableViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
        viewModel.fetchStarships(true)

    }

    private fun setObservers() {
        with(viewModel) {
            starships.observe(viewLifecycleOwner) {
                // Sample - >testing fetching data
                binding.textDashboard.text = it.map { it.name }.toString()
            }
            isLoading.observe(viewLifecycleOwner) {

            }
            errorMessage.observe(viewLifecycleOwner) {

            }
        }
    }

    override fun onDestroyView() {
        viewModel.onDestroyView()
        super.onDestroyView()
    }
}