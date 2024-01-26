package com.polizas.polizasregistry.components.buttons.mainbutton.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.polizasregistry.R

sealed class ButtonType() {
    object Affirmative : ButtonType()
    object Negative : ButtonType()
    object MainAffirmative : ButtonType()
}

@Composable
fun MainButton(isEnabled: Boolean, onClickButton: () -> Unit, buttonType: ButtonType) {
    when (buttonType) {
        ButtonType.Affirmative -> {
            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0XFF02177f)
                ),
                enabled = isEnabled,
                onClick = {
                    onClickButton()
                },
                modifier = Modifier
                    .padding(top = 16.dp, start = 10.dp, end = 10.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.ButtonAfirmative),
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }
        }
        ButtonType.Negative ->{
            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White
                ),
                enabled = isEnabled,
                onClick = {
                    onClickButton()
                },
                modifier = Modifier
                    .padding(top = 16.dp, start = 10.dp, end = 10.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.ButtonNegative),
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
            }
        }
        ButtonType.MainAffirmative ->{
            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0XFF02177f)
                ),
                enabled = isEnabled,
                onClick = {
                    onClickButton()
                },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.ButtonAfirmative),
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }
        }

    }

}