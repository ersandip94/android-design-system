package com.codewithsandip.feature.auth.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.codewithsandip.ds.component.button.CWSButton
import com.codewithsandip.ds.component.button.CWSButtonSize
import com.codewithsandip.ds.component.button.CWSButtonVariant
import com.codewithsandip.ds.component.textfield.CWSTextField
import com.codewithsandip.ds.theme.CWSTheme
import com.codewithsandip.ds.theme.LocalCWSColorScheme
import com.codewithsandip.feature.auth.AuthEvent
import com.codewithsandip.feature.auth.AuthLightDarkPreview

/** Stateful login screen: collects state from the [LoginViewModel] and bridges its one-shot events. */
@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onNavigateToSignup: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    androidx.compose.runtime.LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when (event) {
                AuthEvent.Authenticated -> onLoginSuccess()
            }
        }
    }

    LoginContent(
        uiState = uiState,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onSubmit = viewModel::onSubmit,
        onNavigateToSignup = onNavigateToSignup,
        modifier = modifier,
    )
}

/** Stateless login UI — easy to preview and test. */
@Composable
fun LoginContent(
    uiState: LoginUiState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSubmit: () -> Unit,
    onNavigateToSignup: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(text = "Welcome back", style = MaterialTheme.typography.headlineMedium)
        Text(text = "Sign in to continue", style = MaterialTheme.typography.bodyMedium)

        uiState.errorMessage?.let { message ->
            Text(
                text = message,
                color = LocalCWSColorScheme.current.error,
                style = MaterialTheme.typography.bodyMedium,
            )
        }

        CWSTextField(
            value = uiState.email,
            onValueChange = onEmailChange,
            modifier = Modifier.fillMaxWidth(),
            label = "Email",
            placeholder = "you@example.com",
            isError = uiState.emailError != null,
            errorText = uiState.emailError,
            leadingIcon = Icons.Default.Email,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next),
        )

        CWSTextField(
            value = uiState.password,
            onValueChange = onPasswordChange,
            modifier = Modifier.fillMaxWidth(),
            label = "Password",
            placeholder = "Your password",
            isError = uiState.passwordError != null,
            errorText = uiState.passwordError,
            leadingIcon = Icons.Default.Lock,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
        )

        CWSButton(
            text = "Sign in",
            onClick = onSubmit,
            modifier = Modifier.fillMaxWidth(),
            enabled = uiState.canSubmit,
            loading = uiState.isSubmitting,
        )

        CWSButton(
            text = "No account? Sign up",
            onClick = onNavigateToSignup,
            modifier = Modifier.fillMaxWidth(),
            variant = CWSButtonVariant.Ghost,
            size = CWSButtonSize.Small,
        )
    }
}

@AuthLightDarkPreview
@Composable
private fun LoginContentPreview() {
    CWSTheme {
        LoginContent(
            uiState = LoginUiState(email = "demo@codewithsandip.com", password = "secret123"),
            onEmailChange = {},
            onPasswordChange = {},
            onSubmit = {},
            onNavigateToSignup = {},
        )
    }
}

@AuthLightDarkPreview
@Composable
private fun LoginContentErrorPreview() {
    CWSTheme {
        LoginContent(
            uiState = LoginUiState(
                email = "demo@codewithsandip.com",
                password = "x",
                errorMessage = "Incorrect email or password.",
            ),
            onEmailChange = {},
            onPasswordChange = {},
            onSubmit = {},
            onNavigateToSignup = {},
        )
    }
}
