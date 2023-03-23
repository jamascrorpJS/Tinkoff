package com.jamascrorp.tinkoff.domain.repository

import com.jamascrorp.tinkoff.data.database.models.operations.OperationsModelDB
import com.jamascrorp.tinkoff.data.database.models.pay.PayModelItem

interface CacheDataSource {
    suspend fun saveOperationsToCache(operationsModelItem: List<OperationsModelDB>)
    suspend fun getOperationsFromCache(): List<OperationsModelDB>

    suspend fun savePaysToCache(payModelDBItem: List<PayModelItem>)
    suspend fun getPaysFromCache(): List<PayModelItem>
}