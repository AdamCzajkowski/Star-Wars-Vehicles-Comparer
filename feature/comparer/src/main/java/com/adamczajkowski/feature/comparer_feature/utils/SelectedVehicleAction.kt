package com.adamczajkowski.feature.comparer_feature.utils

import com.adamczajkowski.common.models.Starship

interface SelectedVehicleAction {
    fun selectedVehicle(starship: Starship)
    fun onDialogDismiss()
}