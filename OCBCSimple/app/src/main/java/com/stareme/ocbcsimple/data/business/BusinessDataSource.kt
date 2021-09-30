package com.stareme.ocbcsimple.data.business

import com.stareme.ocbcsimple.http.RetrofitService
import com.stareme.ocbcsimple.http.model.*
import io.reactivex.Observable
import okhttp3.ResponseBody

class BusinessDataSource {

    fun fetchBalance(userToken: String): Observable<BalanceResponse> {
        return RetrofitService.getApi().balance(userToken)
    }

    fun fetchTransactions(userToken: String): Observable<TransactionResponse> {
        return RetrofitService.getApi().transactions(userToken)
    }

    fun fetchPayees(userToken: String): Observable<PayeesResponse> {
        return RetrofitService.getApi().payees(userToken)
    }

    fun postTransfer(userToken: String, bodyTransfer: TransferBody): Observable<TransferResponse> {
        return RetrofitService.getApi().transfer(userToken, bodyTransfer)
    }
}