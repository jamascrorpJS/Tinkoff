package com.jamascrorp.tinkoff.domain.use_cases

import androidx.lifecycle.LiveData
import com.jamascrorp.tinkoff.domain.entity.OperationsModel
import com.jamascrorp.tinkoff.domain.repository.OperationsRepository
import javax.inject.Inject

class GetOperationsUseCase @Inject constructor(private val repository: OperationsRepository) {

    fun getException(): LiveData<String> {
        return repository.getException()
    }

    suspend operator fun invoke(): List<OperationsModel>? {
        return repository.getOperations()
    }
}