package com.stareme.ocbcsimple.ui.login

import com.stareme.ocbcsimple.data.LoginDataSource
import com.stareme.ocbcsimple.data.LoginRepository
import junit.framework.TestCase
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.jupiter.api.Test

class LoginViewModelTest : TestCase() {
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var loginRepository: LoginRepository
    private lateinit var dataSource: LoginDataSource

    @Before
    fun setupViewModel() {
        dataSource = LoginDataSource()
        loginRepository = LoginRepository(dataSource)
        loginViewModel = LoginViewModel(loginRepository)
    }

    @Test
    fun loginTest() {
        loginViewModel.login("ocbc", "123456")

        loginViewModel.loginResult.observeForTesting {
            assertThat(loginViewModel.loginResult.getOrAwaitValue().success).isNotNull
        }
    }
}