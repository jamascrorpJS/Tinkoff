package com.jamascrorp.tinkoff.domain.use_cases

import com.jamascrorp.tinkoff.domain.repository.LocalDataSourceRepository
import javax.inject.Inject

class GetDelFromDBUseCase @Inject constructor(private val repository: LocalDataSourceRepository) {

    suspend fun delOperationsFromDB() {
        repository.delOperationsFromDB()
    }
}