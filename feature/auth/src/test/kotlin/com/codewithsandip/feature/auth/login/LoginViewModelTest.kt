package com.codewithsandip.feature.auth.login

import app.cash.turbine.test
import com.codewithsandip.domain.common.AppError
import com.codewithsandip.domain.common.appFailure
import com.codewithsandip.domain.usecase.LoginUseCase
import com.codewithsandip.domain.validation.ValidateEmail
import com.codewithsandip.domain.validation.ValidatePassword
import com.codewithsandip.feature.auth.AuthEvent
import com.codewithsandip.feature.auth.FakeAuthRepository
import com.codewithsandip.feature.auth.MainDispatcherRule
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Rule
import org.junit.Test

class LoginViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private fun viewModel(repo: FakeAuthRepository = FakeAuthRepository()) =
        LoginViewModel(LoginUseCase(repo), ValidateEmail(), ValidatePassword())

    @Test
    fun invalidInput_showsFieldErrors_andDoesNotCallRepository() = runTest {
        val repo = FakeAuthRepository()
        val vm = viewModel(repo)

        vm.onEmailChange("notanemail")
        vm.onPasswordChange("short")
        vm.onSubmit()

        val state = vm.uiState.value
        assertNotNull(state.emailError)
        assertNotNull(state.passwordError)
        assertFalse(state.isSubmitting)
        assertNull(repo.lastLoginCredentials)
    }

    @Test
    fun validCredentials_success_emitsAuthenticated() = runTest {
        val vm = viewModel(FakeAuthRepository())

        vm.onEmailChange("demo@codewithsandip.com")
        vm.onPasswordChange("password123")

        vm.events.test {
            vm.onSubmit()
            assertEquals(AuthEvent.Authenticated, awaitItem())
        }
        assertFalse(vm.uiState.value.isSubmitting)
    }

    @Test
    fun loginFailure_setsErrorMessage() = runTest {
        val vm = viewModel(FakeAuthRepository(loginResult = appFailure(AppError.InvalidCredentials)))

        vm.onEmailChange("demo@codewithsandip.com")
        vm.onPasswordChange("password123")
        vm.onSubmit()

        val state = vm.uiState.value
        assertEquals("Incorrect email or password.", state.errorMessage)
        assertFalse(state.isSubmitting)
    }

    @Test
    fun typing_clearsPreviousErrors() = runTest {
        val vm = viewModel()
        vm.onSubmit() // triggers field errors on empty input
        assertNotNull(vm.uiState.value.emailError)

        vm.onEmailChange("demo@codewithsandip.com")
        assertNull(vm.uiState.value.emailError)
    }
}
