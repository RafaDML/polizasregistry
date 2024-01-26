package com.polizas.polizasregistry.components.forms.textfield.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InputScreen(
    visualT: VisualTransformation,
    texto: String,
    keyboardOptions: KeyboardOptions,
    onTextChange: (String) -> Unit,
) {
    TextFieldExample(visualT, texto, onTextChange, keyboardOptions)

}

@Composable
fun TextFieldExample(
    visualT: VisualTransformation,
    texto: String,
    onTextChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions
) {
    CustomTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        value = texto,
        onValueChange = { onTextChange(it) },
        textColor = Color.Black, // Customize text color
        backgroundColor = Color.White, // Customize background color
        borderColor = Color.Black, // Customize border color
        borderWidth = 2, // Customize border width
        visualTransformation = visualT,
        keyboardOptions = keyboardOptions
    )
}

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    textColor: Color = Color.Black,
    backgroundColor: Color = Color.White,
    borderColor: Color = Color.Gray,
    borderWidth: Int = 1,
    visualTransformation: VisualTransformation,
    keyboardOptions: KeyboardOptions
) {
    BasicTextField(
        modifier = modifier
            .border(width = borderWidth.dp, color = borderColor, shape = RoundedCornerShape(7.dp))
            .background(backgroundColor, CircleShape)
            .height(40.dp),
        value = value,
        keyboardOptions = keyboardOptions,
        onValueChange = onValueChange,
        textStyle = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp, color = textColor
        ),
        singleLine = true,
        visualTransformation = visualTransformation,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp),
                contentAlignment = Alignment.CenterStart
            ) {

                innerTextField()
            }
        }
    )
}