package com.adamczajkowski.common.utils

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.CallSuper
import androidx.viewbinding.ViewBinding
import com.adamczajkowski.common.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheetDialogFragment<VB : ViewBinding>(
    private val inflate: Inflate<VB>,
) : BottomSheetDialogFragment() {

    private var _binding: VB? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
      val bottomSheetDialog = super.onCreateDialog(savedInstanceState)
      bottomSheetDialog.setOnShowListener(::onDialogShown)
      return bottomSheetDialog
    }

    @CallSuper
    protected open fun onDialogShown(dialogInterface: DialogInterface) {
      val designBottomSheet = dialog?.findViewById<FrameLayout>(R.id.design_bottom_sheet) ?: return
      val behavior = BottomSheetBehavior.from(designBottomSheet)
      behavior.skipCollapsed = true
      behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }
}