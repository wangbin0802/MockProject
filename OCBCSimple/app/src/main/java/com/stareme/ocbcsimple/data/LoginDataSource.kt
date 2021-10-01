package com.stareme.ocbcsimple.data

import com.stareme.ocbcsimple.data.storage.SharePrefManager
import com.stareme.ocbcsimple.data.storage.SharePrefManager.USER_TOKEN_KEY
import com.stareme.ocbcsimple.http.RetrofitService
import com.stareme.ocbcsimple.http.model.BodyLogin
import com.stareme.ocbcsimple.http.model.LoginResponse
import io.reactivex.Observable

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
open class LoginDataSource {


    fun login(username: String, password: String): Observable<LoginResponse> {
       return RetrofitService.getApi().login(BodyLogin(username, password))
    }

    fun logout() {
        // TODO: revoke authentication
        SharePrefManager.putString(USER_TOKEN_KEY, "")
    }
}