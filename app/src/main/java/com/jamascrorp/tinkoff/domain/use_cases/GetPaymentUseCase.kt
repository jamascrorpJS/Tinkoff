package com.jamascrorp.tinkoff.domain.use_cases

import androidx.lifecycle.LiveData
import com.jamascrorp.tinkoff.data.network.Network
import com.jamascrorp.tinkoff.data.network.models.operations.OperationsModelItem
import com.jamascrorp.tinkoff.domain.repository.PaymentRepository
import okhttp3.ResponseBody
import javax.inject.Inject

class GetPaymentUseCase @Inject constructor(private val repository: PaymentRepository) {


    fun getExcept(): LiveData<String> {
        return repository.getExcept()
    }
    suspend fun initResponse(operationsModelItem: OperationsModelItem) {
        repository.postPays(operationsModelItem)
    }

    operator fun invoke(): LiveData<Network<ResponseBody>> {
        return repository.getPayments()
    }
}