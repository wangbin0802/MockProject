package com.stareme.ocbcsimple.data.business

import com.stareme.ocbcsimple.data.storage.SharePrefManager
import com.stareme.ocbcsimple.http.model.BalanceResponse
import com.stareme.ocbcsimple.http.model.PayeesResponse
import com.stareme.ocbcsimple.http.model.TransactionResponse
import com.stareme.ocbcsimple.http.model.TransferResponse
import io.reactivex.Observable

class BusinessRepository(private val dataSource: BusinessDataSource) {

    private var userToken: String = getToken()

    fun fetchBalance(): Observable<BalanceResponse> {
        return dataSource.fetchBalance(userToken)
    }

    fun fetchTransactions(): Observable<TransactionResponse> {
        return dataSource.fetchTransactions(userToken)
    }

    fun fetchPayees(): Observable<PayeesResponse> {
        return dataSource.fetchPayees(userToken)
    }

    fun postTransfer(): Observable<TransferResponse> {
        return dataSource.postTransfer(userToken)
    }

    private fun getToken(): String {
        if (userToken.isEmpty()) {
            userToken = SharePrefManager.getString(SharePrefManager.USER_TOKEN_KEY)!!
        }
        return userToken
    }
}