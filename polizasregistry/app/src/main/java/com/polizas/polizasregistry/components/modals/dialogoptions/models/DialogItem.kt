package com.polizas.polizasregistry.components.modals.dialogoptions.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.graphics.vector.ImageVector

data class DialogOptionsItem(
    var show: Boolean = false,
    var msg: String = "",
    val icon: ImageVector = Icons.Filled.Info
)
