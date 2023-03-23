package com.jamascrorp.tinkoff.data.repository

import com.jamascrorp.tinkoff.data.network.TinkoffApi
import com.jamascrorp.tinkoff.data.network.models.operations.OperationsModel
import com.jamascrorp.tinkoff.data.network.models.operations.OperationsModelItem
import com.jamascrorp.tinkoff.data.network.models.pay.PayModel
import com.jamascrorp.tinkoff.domain.repository.NetworkDataSource
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class NetworkDataSourceImpl @Inject constructor(
    private val tinkoffApi: TinkoffApi,
) : NetworkDataSource {

    override suspend fun getPays(): Response<PayModel> {
        return tinkoffApi.getCategory()
    }

    override suspend fun postOperations(operations: OperationsModelItem): Response<ResponseBody> {
        return tinkoffApi.postOperations(operations)
    }

    override suspend fun getOperations(): Response<OperationsModel> {
        return tinkoffApi.getOperations()
    }
}