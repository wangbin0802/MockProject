package com.stareme.ocbcsimple.data.business

import com.stareme.ocbcsimple.data.storage.SharePrefManager
import com.stareme.ocbcsimple.http.model.*
import io.reactivex.Observable
import okhttp3.ResponseBody

class BusinessRepository(private val dataSource: BusinessDataSource) {

    private val userToken by lazy {
        getToken()
    }

    fun fetchBalance(): Observable<BalanceResponse> {
        return dataSource.fetchBalance(userToken)
    }

    fun fetchTransactions(): Observable<TransactionResponse> {
        return dataSource.fetchTransactions(userToken)
    }

    fun fetchPayees(): Observable<PayeesResponse> {
        return dataSource.fetchPayees(userToken)
    }

    fun postTransfer(transferBody: TransferBody): Observable<TransferResponse> {
        return dataSource.postTransfer(userToken, transferBody)
    }

    private fun getToken(): String {
        return SharePrefManager.getString(SharePrefManager.USER_TOKEN_KEY)!!
    }
}