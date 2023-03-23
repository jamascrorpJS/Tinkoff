package com.jamascrorp.tinkoff.data.network

import com.jamascrorp.tinkoff.data.network.models.operations.OperationsModel
import com.jamascrorp.tinkoff.data.network.models.pay.PayModel
import com.jamascrorp.tinkoff.data.network.models.operations.OperationsModelItem
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface TinkoffApi {

    @GET("pay")
    suspend fun getCategory(): Response<PayModel>

    @POST("operations")
    @Headers(
        "Content-Type: application/json",
        "Accept: application/json"
    )
    suspend fun postOperations(
        @Body operations:OperationsModelItem
    ): Response<ResponseBody>

    @GET("operations")
    suspend fun getOperations(): Response<OperationsModel>
}