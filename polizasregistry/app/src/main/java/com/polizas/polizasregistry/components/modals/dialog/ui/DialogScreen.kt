package com.polizas.polizasregistry.components.modals.dialog.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun DialogScreen(
    msg: String,
    icon: ImageVector,
    onClickAcceptButton: (()-> Unit)
) {
    Dialog(
        onDismissRequest = {},
        properties = DialogProperties(
            dismissOnBackPress = false, dismissOnClickOutside = false,
            usePlatformDefaultWidth = false
        )
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp),
            elevation = 4.dp,
            shape = RoundedCornerShape(16.dp)
        ) {
            Box(
                Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
                    .padding(25.dp)
                    .fillMaxWidth(),
            ) {
                Column() {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            modifier = Modifier.size(50.dp),
                            imageVector = icon,
                            contentDescription = null,
                            tint = Color.Red,

                            )
                    }
                    Spacer(
                        modifier = Modifier
                            .width(15.dp)
                            .height(12.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(text = msg, fontWeight = FontWeight.SemiBold, fontSize = 20.sp)
                    }


                    Button(
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xFF008080)
                        ),
                        enabled = true,
                        onClick = {
                            onClickAcceptButton()
                        },
                        modifier = Modifier 
                            .padding(top = 16.dp, start = 10.dp, end = 10.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Aceptar",
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                    }

                }
            }
        }
    }
}