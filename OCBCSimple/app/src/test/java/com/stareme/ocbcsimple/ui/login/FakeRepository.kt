package com.stareme.ocbcsimple.ui.login

import com.stareme.ocbcsimple.data.LoginDataSource
import com.stareme.ocbcsimple.data.LoginRepository

class FakeRepository(dataSource: LoginDataSource) : LoginRepository(dataSource)