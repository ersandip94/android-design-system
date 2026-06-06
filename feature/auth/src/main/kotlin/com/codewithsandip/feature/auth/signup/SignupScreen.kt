package com.codewithsandip.feature.auth.signup

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
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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

/** Stateful signup screen. */
@Composable
fun SignupScreen(
    onSignupSuccess: () -> Unit,
    onNavigateToLogin: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SignupViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when (event) {
                AuthEvent.Authenticated -> onSignupSuccess()
            }
        }
    }

    SignupContent(
        uiState = uiState,
        onNameChange = viewModel::onNameChange,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onSubmit = viewModel::onSubmit,
        onNavigateToLogin = onNavigateToLogin,
        modifier = modifier,
    )
}

/** Stateless signup UI. */
@Composable
fun SignupContent(
    uiState: SignupUiState,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSubmit: () -> Unit,
    onNavigateToLogin: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(text = "Create account", style = MaterialTheme.typography.headlineMedium)
        Text(text = "Join CodeWithSandip", style = MaterialTheme.typography.bodyMedium)

        uiState.errorMessage?.let { message ->
            Text(
                text = message,
                color = LocalCWSColorScheme.current.error,
                style = MaterialTheme.typography.bodyMedium,
            )
        }

        CWSTextField(
            value = uiState.name,
            onValueChange = onNameChange,
            modifier = Modifier.fillMaxWidth(),
            label = "Name",
            placeholder = "Your name",
            isError = uiState.nameError != null,
            errorText = uiState.nameError,
            leadingIcon = Icons.Default.Person,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        )

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
            placeholder = "At least 8 characters",
            isError = uiState.passwordError != null,
            errorText = uiState.passwordError,
            leadingIcon = Icons.Default.Lock,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
        )

        CWSButton(
            text = "Create account",
            onClick = onSubmit,
            modifier = Modifier.fillMaxWidth(),
            enabled = uiState.canSubmit,
            loading = uiState.isSubmitting,
        )

        CWSButton(
            text = "Have an account? Sign in",
            onClick = onNavigateToLogin,
            modifier = Modifier.fillMaxWidth(),
            variant = CWSButtonVariant.Ghost,
            size = CWSButtonSize.Small,
        )
    }
}

@AuthLightDarkPreview
@Composable
private fun SignupContentPreview() {
    CWSTheme {
        SignupContent(
            uiState = SignupUiState(name = "Sandip", email = "sandip@codewithsandip.com", password = "secret123"),
            onNameChange = {},
            onEmailChange = {},
            onPasswordChange = {},
            onSubmit = {},
            onNavigateToLogin = {},
        )
    }
}
