package com.codewithsandip.feature.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codewithsandip.domain.common.appErrorOrNull
import com.codewithsandip.domain.model.Credentials
import com.codewithsandip.domain.usecase.LoginUseCase
import com.codewithsandip.domain.validation.ValidateEmail
import com.codewithsandip.domain.validation.ValidatePassword
import com.codewithsandip.feature.auth.AuthEvent
import com.codewithsandip.feature.auth.toAuthMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val login: LoginUseCase,
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword,
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    private val _events = Channel<AuthEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    fun onEmailChange(value: String) {
        _uiState.update { it.copy(email = value, emailError = null, errorMessage = null) }
    }

    fun onPasswordChange(value: String) {
        _uiState.update { it.copy(password = value, passwordError = null, errorMessage = null) }
    }

    fun onSubmit() {
        val current = _uiState.value
        val emailError = validateEmail(current.email).errorOrNull
        val passwordError = validatePassword(current.password).errorOrNull
        if (emailError != null || passwordError != null) {
            _uiState.update { it.copy(emailError = emailError, passwordError = passwordError) }
            return
        }

        _uiState.update { it.copy(isSubmitting = true, errorMessage = null) }
        viewModelScope.launch {
            val result = login(Credentials(current.email, current.password))
            result
                .onSuccess {
                    _uiState.update { it.copy(isSubmitting = false) }
                    _events.send(AuthEvent.Authenticated)
                }
                .onFailure {
                    _uiState.update {
                        it.copy(isSubmitting = false, errorMessage = result.appErrorOrNull().toAuthMessage())
                    }
                }
        }
    }
}
