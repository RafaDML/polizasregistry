package com.polizas.polizasregistry.components.modals.loading.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun LoadingScreen(
    progressIndicatorColor: Color = Color(0xFF008080),
    progressIndicatorSize: Dp = 4.dp,
    msg: String =  "Cargando"
) {
    val paddingBottom: Dp = 32.dp

    Dialog(
        onDismissRequest = {
        },
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false,
            usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            elevation = 4.dp,
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
                    .padding(35.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
                    Text(
                        modifier = Modifier
                            .padding(bottom = paddingBottom),
                        text = msg,
                        style = TextStyle(
                            color = Color.Black,
                            textAlign = TextAlign.Center,
                            fontSize = 16.sp
                        )
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    CircularProgressIndicator(
                        modifier = Modifier.size(15.dp).padding(top = 4.dp),
                        color = progressIndicatorColor,
                        strokeWidth = progressIndicatorSize
                    )
                }
            }
        }
    }
}
