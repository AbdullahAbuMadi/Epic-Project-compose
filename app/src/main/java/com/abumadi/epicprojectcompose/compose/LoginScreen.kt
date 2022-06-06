package com.abumadi.epicprojectcompose.compose

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.abumadi.epicprojectcompose.ui.theme.Shapes
import com.abumadi.epicprojectcompose.ui.theme.TextFieldStrokeColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.abumadi.epicprojectcompose.R

@Composable
fun LoginScreen() {
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var isValid by remember { mutableStateOf(false) }

    Column(
        Modifier
            .background(color = colorResource(id = R.color.AppColor))
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_epic_icon),
            contentDescription = "login screen icon",
            Modifier
                .width(143.dp)
                .height(168.dp)
        )
        if (isValid) {
            CircularIndeterminateProgressBar(true)
        } else {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutLineTextFieldSample(
                    email,
                    { email = it },
                    { email = it },
                    KeyboardType.Email,
                    "Email",
                    false
                )
                OutLineTextFieldSample(
                    password,
                    { password = it },
                    { password = it },
                    KeyboardType.Password,
                    "Password",
                    true
                )
                CustomButton(email, password) { isValid = it }
                CustomText()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainDefaultPreview() {
    LoginScreen()
}

@Composable
fun OutLineTextFieldSample(
    text: TextFieldValue,
    emailOnValueChange: (TextFieldValue) -> Unit,
    passwordOnValueChange: (TextFieldValue) -> Unit,
    keyboardType: KeyboardType,
    labelText: String,
    isPassword: Boolean,
    textStyle: TextStyle = MaterialTheme.typography.body2,
    shapes: Shape = Shapes.large
) {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp, bottom = 26.dp)
    ) {
        OutlinedTextField(
            value = text,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            label = { Text(text = labelText, Modifier.padding(start = 10.dp), style = textStyle) },
            onValueChange = if (!isPassword) emailOnValueChange
            else passwordOnValueChange,
            textStyle = LocalTextStyle.current,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = White,
                unfocusedBorderColor = TextFieldStrokeColor,
                unfocusedLabelColor = White,
                focusedLabelColor = White,
                textColor = White
            ),
            shape = shapes,
            visualTransformation = if (!isPassword) VisualTransformation.None
            else PasswordVisualTransformation()
        )
        //add text to take error message inside it....
    }
}

@Composable
fun CustomButton(
    email: TextFieldValue,
    password: TextFieldValue,
    isValid: (Boolean) -> (Unit),
) {
    val context = LocalContext.current
    var emailErrorText: String? = null
    var passwordErrorText: String? = null
    val showDialog = remember {
        mutableStateOf(false)
    }
    Box(
        Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp, bottom = 26.dp)
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            onClick = {
                if (Validation.validateEmail(email = email.text) && Validation.validatePassword(
                        password = password.text
                    )
                ) {
                    isValid(true)
                    Toast.makeText(context, "login", Toast.LENGTH_SHORT).show()
                } else {
                    //email
                    val emailInput = email.text.trim { it <= ' ' }
                    val isEmailValid = Validation.validateEmail(emailInput)

                    if (isEmailValid) {
                        emailErrorText = null
                    } else {
                        if (emailInput.isEmpty()) {
                            emailErrorText = "Field can't be empty"
                            Toast.makeText(
                                context,
                                emailErrorText,
                                Toast.LENGTH_SHORT
                            ).show()
                        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput ?: "").matches()) {
                            emailErrorText = "Please enter a valid email address"
                            Toast.makeText(
                                context,
                                emailErrorText,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                    //password
                    val passwordInput = password.text.trim { it <= ' ' }
                    val isPasswordValid = Validation.validatePassword(passwordInput)

                    if (isPasswordValid) {
                        passwordErrorText = null
                    } else {
                        if (passwordInput.isEmpty()) {
                            passwordErrorText = "Field can't be empty"
                            Toast.makeText(
                                context,
                                passwordErrorText,
                                Toast.LENGTH_SHORT
                            ).show()

                        } else if (!Validation.PASSWORD_PATTERN.matcher(passwordInput ?: "")
                                .matches()
                        ) {
                            passwordErrorText = "Password too weak"
                            Toast.makeText(
                                context,
                                passwordErrorText,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            },
            shape = Shapes.large,
            colors =
            ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.ButtonBackgroundColor))
        ) {
            Text(
                text = "Login",
                color = colorResource(id = R.color.AppColor),
                style = MaterialTheme.typography.h1
            )
        }
    }
    if (showDialog.value) {
        CircularIndeterminateProgressBar(true)

    } else {
        CircularIndeterminateProgressBar(false)
    }
}

@Composable
fun CustomText() {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp, bottom = 26.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Forgot your password?",
            style = MaterialTheme.typography.h1,
            modifier = Modifier
                .padding(start = 24.dp, end = 24.dp),
            color = White
        )
    }
}

@Composable
fun CircularIndeterminateProgressBar(isDisplayed: Boolean) {
    if (isDisplayed) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.Top
        ) {
            CircularProgressIndicator(
                color = MaterialTheme.colors.primary
            )
        }
    }
}

