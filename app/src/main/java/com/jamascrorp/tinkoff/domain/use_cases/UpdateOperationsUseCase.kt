package com.jamascrorp.tinkoff.domain.use_cases

import com.jamascrorp.tinkoff.domain.entity.OperationsModel
import com.jamascrorp.tinkoff.domain.repository.OperationsRepository
import javax.inject.Inject

class UpdateOperationsUseCase @Inject constructor(private val repository: OperationsRepository) {


    suspend operator fun invoke(): List<OperationsModel>? {
        return repository.updateOperations()
    }
}