package com.example.jectpackdemo.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.jectpackdemo.bean.dto.UserDto

@Composable
fun LoginScreen(viewModel : LoginViewModel = hiltViewModel(),onLoggedIn : (UserDto) -> Unit) {

    /*val currentOnLoggedIn by rememberUpdatedState(onLoggedIn)

    val logged = viewModel.logged.collectAsStateWithLifecycle().value
    if (logged != null) {
        onLoggedIn.invoke(logged)
    }*/
    Column(modifier = Modifier.fillMaxSize()) {
        LoginInputEditText("用户名",viewModel.username.collectAsState().value,{
            viewModel.onChangedUsername(it)
        })
        Spacer(modifier = Modifier.height(12.dp))
        LoginInputEditText("密码",viewModel.password.collectAsState().value,{
            viewModel.onChangedPassword(it)
        })

        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = viewModel::toLogin) {
            Text("登录")
        }
    }
}

@Composable
fun LoginInputEditText(label: String,value : String,onValueChanged : (String) -> Unit, modifier: Modifier = Modifier) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Gray, shape = RoundedCornerShape(4.dp)),
        textStyle = TextStyle(
            fontSize = 16.sp,
            color = Color.Red,
            textDecoration = TextDecoration.None
        ),
        label = { label }
    )
}