package com.example.polizasregistry.Polizas.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.polizasregistry.R
import com.example.polizasregistry.navigation.AppScreens
@Composable
fun PolizaScreen(
    navigate: (AppScreens) -> Unit,

    ) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()

    ) {
        Header()
        Body(modifier = Modifier.weight(1f))

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Header() {
    Box(
        Modifier
            .fillMaxWidth()
            .background(Color(0xFF317d8c))
            .padding(10.dp)
    ) {
        Text(
            text = "Polizas", style = TextStyle(
                color = Color.White,
                fontSize = 21.sp,
                lineHeight = 27.sp
            )
        )
    }
}

@Composable
fun Body(modifier: Modifier) {
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())

    ) {
        CardItemPoliza()


    }


}

@Composable
fun CardItemPoliza() {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 18.dp)
            .height(35.dp)
    ) {
        Row(
            Modifier
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(16.dp, 16.dp, 0.dp, 0.dp))
                .background(Color(0XFF006FB9)), verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = "Póliza No. 1",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 21.sp,
                    lineHeight = 27.sp,
                    fontWeight = FontWeight.Bold
                ),

                )
        }

    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 0.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        shape = RoundedCornerShape(0.dp, 0.dp, 16.dp, 16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White, contentColor = Color.Cyan)

    ) {
        Column(

        ) {
            CardContentPoliza(stringResource(id = R.string.PolizaCardContentArticulo),"RELOJ")
            CardContentPoliza(stringResource(id = R.string.PolizaCardContentCantidad),"10")
            CardContentPoliza(stringResource(id = R.string.PolizaCardContentEmpleado),"Fernanda Serna")
            CardContentPoliza(stringResource(id = R.string.PolizaCardContentFecha),"2023-12-12")

        }
    }
}

@Composable
fun CardContentPoliza(description : String, valueDesc : String){
    Box(Modifier.padding(horizontal = 15.dp, vertical = 5.dp), contentAlignment = Alignment.Center) {
        Text(
            text = description + valueDesc,
            style = TextStyle(
                color = Color.Black,
                fontSize = 18.sp,
                lineHeight = 27.sp
            ),
            color = Color.Black
        )
    }
}

@Composable
fun Footer(modifier: Modifier) {
    Box(modifier = modifier)

}