package com.polizas.polizasregistry.login.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.polizas.polizasregistry.navigation.AppScreens
import com.example.polizasregistry.R
import com.polizas.polizasregistry.components.modals.dialog.models.DialogItem
import com.polizas.polizasregistry.components.modals.dialog.ui.DialogScreen
import com.polizas.polizasregistry.components.modals.loading.ui.LoadingScreen
import com.polizas.polizasregistry.login.viewmodel.LoginViewModel


@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel(),
    navigate: (AppScreens) -> Unit,

    ) {
    loginViewModel.entrarScaffold(navigate)
    showModals(loginViewModel)

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {

            LoginHeader()
            LoginBody(loginViewModel, navigate)
        }
    }

}

@Composable
fun showModals( viewModel: LoginViewModel) {
    val loading: Boolean by viewModel.isLoading.observeAsState(initial = false)
    val showDialog: DialogItem by viewModel.dialogInfo.observeAsState(DialogItem())
    if (loading){
        LoadingScreen()
    }
    if(showDialog.show){
        DialogScreen(showDialog.msg, showDialog.icon, onClickAcceptButton = {viewModel.closeModal(false,"")})
    }
}

@Composable
fun LoginHeader() {
    Box(
        Modifier
            .fillMaxWidth()
            .background(Color(0XFF02177f))
            .padding(10.dp)
    ) {
        Text(
            text = "Login", style = TextStyle(
                color = Color.White,
                fontSize = 20.sp,
                lineHeight = 27.sp
            )
        )
    }
}

@Composable
fun CustomizedTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    textColor: Color = Color.Black,
    backgroundColor: Color = Color.White,
    borderColor: Color = Color.Gray,
    borderWidth: Int = 1,
    visualTransformation: VisualTransformation
) {
    BasicTextField(
        modifier = modifier
            .border(width = borderWidth.dp, color = borderColor, shape = RoundedCornerShape(7.dp))
            .background(backgroundColor, CircleShape)
            .height(40.dp),
        value = value,
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
                    .padding(start = 15.dp),
                contentAlignment = Alignment.CenterStart
            ) {

                innerTextField()
            }
        }
    )
}

@Composable
fun TextFieldExample(
    visualT: VisualTransformation,
    texto: String,
    onTextChange: (String) -> Unit
) {
    CustomizedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        value = texto,
        onValueChange = { onTextChange(it) },
        textColor = Color.Black, // Customize text color
        backgroundColor = Color.White, // Customize background color
        borderColor = Color.Black, // Customize border color
        borderWidth = 2, // Customize border width
        visualTransformation = visualT
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginBody(viewModel: LoginViewModel, navigate: (AppScreens) -> Unit) {

    val username: String by viewModel.username.observeAsState(initial = "rafaelmedina@gmail.com")
    val password: String by viewModel.password.observeAsState(initial = "coppel123")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp)
                .background(Color.White),
            textAlign = TextAlign.Start,
            text = stringResource(id = R.string.LoginUserLabel),
            color = Color.Black,
            fontWeight = FontWeight.SemiBold,

            )
        TextFieldExample(
            VisualTransformation.None,
            username
        ) {
            viewModel.onChangeUser(username = it, password = password)
        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp),
            textAlign = TextAlign.Start,
            text = stringResource(id = R.string.LoginPasswordLabel),
            color = Color.Black,
            fontWeight = FontWeight.SemiBold
        )
        TextFieldExample(
            PasswordVisualTransformation(),
            password,

            ) {
            viewModel.onChangeUser(username = username, password = it)
        }
        ButtonMain(viewModel,username,password,navigate)

    }
}

@Composable
fun ButtonMain(
    viewModel: LoginViewModel,
    username: String,
    password: String,
    navigate: (AppScreens) -> Unit
) {
    val button: Boolean by viewModel.isButtonEnabled.observeAsState(initial = false)

    Button(
        enabled = button,
        onClick = { viewModel.onLoginSelect(viewModel, username, password, navigate) },
        modifier = Modifier
            .padding(top = 16.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0XFF02177f),
            disabledContainerColor = Color(0XFFBDBDBD)
        )
    ) {
        Text(text = "Iniciar sesion", fontWeight = FontWeight.SemiBold)
    }
}