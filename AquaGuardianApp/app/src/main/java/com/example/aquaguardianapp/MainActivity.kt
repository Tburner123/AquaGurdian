package com.example.aquaguardianapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.aquaguardianapp.ui.theme.AquaGuardianAppTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ElevatedButton
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AquaGuardianAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),

                ) {
                    Logins()
                }
            }
        }
    }
}
@Composable
fun MyApp(modifier: Modifier = Modifier) {

    var shouldShowOnboarding by remember { mutableStateOf(true) }

    Surface(modifier= modifier.fillMaxSize(),color=(Color(0xFF00C2FF))) {
        if (shouldShowOnboarding) {
            OnboardingScreen(onContinueClicked = { shouldShowOnboarding = false })
        } else {
            Logins()
        }
    }
}
@Composable
fun OnboardingScreen(
    onContinueClicked: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text="Aqua Guardian",
            modifier = Modifier.padding(top = 30.dp),
            fontSize = 50.sp,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
            color = Color.White,
            fontFamily = FontFamily.Cursive
        )

    }
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Aqua Guardian Logo",
            modifier = Modifier
                .padding(bottom = 40.dp)
                .size(400.dp)

        )
        Button(
            modifier = Modifier.padding(top = 40.dp),
            onClick = onContinueClicked
        ) {
             Text(text = "Login",
                fontSize = 50.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                fontFamily = FontFamily.Cursive)

        }
        Button(
            modifier = Modifier.padding(vertical = 4.dp),
            onClick = onContinueClicked
        ) {
            Text(text ="Register",
                fontSize = 30.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                fontFamily = FontFamily.Cursive
            )
        }
    }
    Column(modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text="Guarding Your Hydration",
            modifier = Modifier.padding(bottom = 60.dp),
            fontSize = 30.sp,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
            color = Color.White,
            fontFamily = FontFamily.Cursive
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField() {
    var password by rememberSaveable { mutableStateOf("") }

    TextField(
        value = password,
        onValueChange = { password = it },
        label = { Text("Enter password") },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun loginTextBox() {
    var text by remember { mutableStateOf("") }

    TextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("Username") },
    )
}

@Composable                                                     
private fun Logins(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Aqua Guardian",
            modifier = Modifier.padding(top = 30.dp),
            fontSize = 50.sp,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
            color = Color.White,
            fontFamily = FontFamily.Cursive
        )
    }
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Aqua Guardian Logo",
            modifier = Modifier
                .padding(top = 40.dp)
                .size(200.dp)

        )
    }
    Column(
        modifier = modifier.padding(bottom = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        loginTextBox()
        Spacer(modifier = Modifier.size(10.dp))
        PasswordTextField()
    }
    Column( modifier = modifier.padding(bottom = 20.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Button(
            modifier = Modifier.padding(top = 20.dp),
            onClick = {}
        ) {
            Text(
                text = "Login",
                fontSize = 40.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                fontFamily = FontFamily.Cursive
            )

        }
    }
}


@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    AquaGuardianAppTheme {
        OnboardingScreen(onContinueClicked = {})
    }
}

@Composable
private fun Login(name: String) {

    val expanded = remember { mutableStateOf(false) }

    val extraPadding = if (expanded.value) 48.dp else 0.dp

    Surface(
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(modifier = Modifier
                .weight(1f)
                .padding(bottom = extraPadding)
            ) {
                Text(text = name)
            }
            ElevatedButton(
                onClick = { expanded.value = !expanded.value }
            ) {
                Text(if (expanded.value) "Show less" else "Show more")
            }
        }
    }
}
@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
    AquaGuardianAppTheme {
        Logins()
    }
}

@Preview
@Composable
fun MyAppPreview() {
    AquaGuardianAppTheme {
        MyApp(Modifier.fillMaxSize())
    }
}
