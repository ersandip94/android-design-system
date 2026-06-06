package com.codewithsandip.feature.auth.signup

import app.cash.turbine.test
import com.codewithsandip.domain.common.AppError
import com.codewithsandip.domain.common.appFailure
import com.codewithsandip.domain.usecase.SignupUseCase
import com.codewithsandip.domain.validation.ValidateEmail
import com.codewithsandip.domain.validation.ValidatePassword
import com.codewithsandip.feature.auth.AuthEvent
import com.codewithsandip.feature.auth.FakeAuthRepository
import com.codewithsandip.feature.auth.MainDispatcherRule
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test

class SignupViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private fun viewModel(repo: FakeAuthRepository = FakeAuthRepository()) =
        SignupViewModel(SignupUseCase(repo), ValidateEmail(), ValidatePassword())

    @Test
    fun blankName_showsNameError() = runTest {
        val repo = FakeAuthRepository()
        val vm = viewModel(repo)

        vm.onEmailChange("new@codewithsandip.com")
        vm.onPasswordChange("password123")
        vm.onSubmit()

        assertNotNull(vm.uiState.value.nameError)
        assertEquals(null, repo.lastSignupName)
    }

    @Test
    fun validInput_success_emitsAuthenticated() = runTest {
        val vm = viewModel(FakeAuthRepository())

        vm.onNameChange("Sandip")
        vm.onEmailChange("new@codewithsandip.com")
        vm.onPasswordChange("password123")

        vm.events.test {
            vm.onSubmit()
            assertEquals(AuthEvent.Authenticated, awaitItem())
        }
        assertFalse(vm.uiState.value.isSubmitting)
    }

    @Test
    fun emailTaken_setsErrorMessage() = runTest {
        val vm = viewModel(FakeAuthRepository(signupResult = appFailure(AppError.EmailTaken)))

        vm.onNameChange("Sandip")
        vm.onEmailChange("demo@codewithsandip.com")
        vm.onPasswordChange("password123")
        vm.onSubmit()

        assertEquals("That email is already registered.", vm.uiState.value.errorMessage)
    }
}
