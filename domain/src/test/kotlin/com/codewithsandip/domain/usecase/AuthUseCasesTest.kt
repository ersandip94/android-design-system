package com.codewithsandip.domain.usecase

import com.codewithsandip.domain.common.AppError
import com.codewithsandip.domain.common.appErrorOrNull
import com.codewithsandip.domain.common.appFailure
import com.codewithsandip.domain.fake.FakeAuthRepository
import com.codewithsandip.domain.model.Credentials
import com.codewithsandip.domain.model.User
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class AuthUseCasesTest {

    @Test
    fun login_returnsRepositoryResult() = runTest {
        val user = User("u9", "demo@codewithsandip.com", "Demo")
        val repo = FakeAuthRepository(loginResult = Result.success(user))
        val login = LoginUseCase(repo)

        val result = login(Credentials("demo@codewithsandip.com", "password123"))

        assertEquals(Result.success(user), result)
    }

    @Test
    fun login_trimsEmailBeforeCallingRepository() = runTest {
        val repo = FakeAuthRepository()
        val login = LoginUseCase(repo)

        login(Credentials("  demo@codewithsandip.com  ", "password123"))

        assertEquals("demo@codewithsandip.com", repo.lastLoginCredentials?.email)
    }

    @Test
    fun login_propagatesFailure() = runTest {
        val repo = FakeAuthRepository(loginResult = appFailure(AppError.InvalidCredentials))
        val login = LoginUseCase(repo)

        val result = login(Credentials("demo@codewithsandip.com", "wrong"))

        assertEquals(AppError.InvalidCredentials, result.appErrorOrNull())
    }

    @Test
    fun signup_trimsNameAndEmail() = runTest {
        val repo = FakeAuthRepository()
        val signup = SignupUseCase(repo)

        signup("  Sandip  ", Credentials("  new@codewithsandip.com  ", "password123"))

        assertEquals("Sandip", repo.lastSignupName)
        assertEquals("new@codewithsandip.com", repo.lastSignupCredentials?.email)
    }

    @Test
    fun signup_returnsRepositoryResult() = runTest {
        val user = User("u2", "new@codewithsandip.com", "Sandip")
        val repo = FakeAuthRepository(signupResult = Result.success(user))
        val signup = SignupUseCase(repo)

        val result = signup("Sandip", Credentials("new@codewithsandip.com", "password123"))

        assertEquals(Result.success(user), result)
    }
}
