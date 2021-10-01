package com.stareme.ocbcsimple.data

import com.stareme.ocbcsimple.data.model.LoggedInUser
import com.stareme.ocbcsimple.data.storage.SharePrefManager
import com.stareme.ocbcsimple.http.model.LoginResponse
import io.reactivex.Observable
import java.util.*

open class LoginRepository(val dataSource: LoginDataSource) {

    var user: LoggedInUser? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        user = null
    }

    fun logout() {
        user = null
        dataSource.logout()
    }

    fun login(username: String, password: String): Observable<LoginResponse> {
        // handle login
        return dataSource.login(username, password).map {
            if (it.status == "success") {
                setLoggedInUser(LoggedInUser(UUID.randomUUID().toString(), username, it.token))
            }
            it
        }
    }

    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.user = loggedInUser
        // TODO local storage
        SharePrefManager.putString(SharePrefManager.USER_TOKEN_KEY, user!!.userToken)
    }
}