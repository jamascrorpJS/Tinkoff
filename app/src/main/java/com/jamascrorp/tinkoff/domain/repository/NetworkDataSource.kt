package com.jamascrorp.tinkoff.domain.repository

import com.jamascrorp.tinkoff.data.network.models.operations.OperationsModel
import com.jamascrorp.tinkoff.data.network.models.operations.OperationsModelItem
import com.jamascrorp.tinkoff.data.network.models.pay.PayModel
import okhttp3.ResponseBody
import retrofit2.Response

interface NetworkDataSource {

    suspend fun getPays(): Response<PayModel>
    suspend fun postOperations(operations: OperationsModelItem): Response<ResponseBody>
    suspend fun getOperations(): Response<OperationsModel>
}