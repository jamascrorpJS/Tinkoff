package com.jamascrorp.tinkoff.domain.repository

import androidx.lifecycle.LiveData
import com.jamascrorp.tinkoff.data.network.Network
import com.jamascrorp.tinkoff.data.network.models.operations.OperationsModelItem
import okhttp3.ResponseBody

interface PaymentRepository {

    fun getExcept(): LiveData<String>
    fun getPayments(): LiveData<Network<ResponseBody>>

    suspend fun postPays(operationsModelItem: OperationsModelItem)
}