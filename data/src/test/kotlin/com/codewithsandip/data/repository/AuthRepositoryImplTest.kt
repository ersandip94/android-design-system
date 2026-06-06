package com.codewithsandip.data.repository

import com.codewithsandip.data.remote.MockAuthApi
import com.codewithsandip.domain.common.AppError
import com.codewithsandip.domain.common.appErrorOrNull
import com.codewithsandip.domain.model.Credentials
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class AuthRepositoryImplTest {

    private fun repo() = AuthRepositoryImpl(MockAuthApi())

    @Test
    fun login_withSeededDemoAccount_succeeds() = runTest {
        val result = repo().login(Credentials("demo@codewithsandip.com", "password123"))

        assertTrue(result.isSuccess)
        assertEquals("Demo User", result.getOrNull()?.displayName)
    }

    @Test
    fun login_wrongPassword_failsWithInvalidCredentials() = runTest {
        val result = repo().login(Credentials("demo@codewithsandip.com", "nope"))

        assertEquals(AppError.InvalidCredentials, result.appErrorOrNull())
    }

    @Test
    fun login_unknownEmail_failsWithInvalidCredentials() = runTest {
        val result = repo().login(Credentials("ghost@codewithsandip.com", "password123"))

        assertEquals(AppError.InvalidCredentials, result.appErrorOrNull())
    }

    @Test
    fun signup_newEmail_succeedsThenCanLogin() = runTest {
        val repo = repo()
        val signup = repo.signup("Sandip", Credentials("sandip@codewithsandip.com", "password123"))
        assertTrue(signup.isSuccess)

        val login = repo.login(Credentials("sandip@codewithsandip.com", "password123"))
        assertTrue(login.isSuccess)
    }

    @Test
    fun signup_existingEmail_failsWithEmailTaken() = runTest {
        val result = repo().signup("Imposter", Credentials("demo@codewithsandip.com", "password123"))

        assertEquals(AppError.EmailTaken, result.appErrorOrNull())
    }
}
