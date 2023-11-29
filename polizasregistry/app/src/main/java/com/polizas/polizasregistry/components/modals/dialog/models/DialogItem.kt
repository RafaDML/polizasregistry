package com.polizas.polizasregistry.components.modals.dialog.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.graphics.vector.ImageVector

data class DialogItem(
    var show: Boolean = false,
    var msg: String = "",
    val icon: ImageVector = Icons.Filled.Info
)
