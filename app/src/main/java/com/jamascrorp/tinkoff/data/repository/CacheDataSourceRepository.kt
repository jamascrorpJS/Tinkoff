package com.jamascrorp.tinkoff.data.repository

import com.jamascrorp.tinkoff.data.database.models.operations.OperationsModelDB
import com.jamascrorp.tinkoff.data.database.models.pay.PayModelItem
import com.jamascrorp.tinkoff.domain.repository.CacheDataSource
import javax.inject.Inject

class CacheDataSourceRepository @Inject constructor() : CacheDataSource {
    private var operationsModelArray = ArrayList<OperationsModelDB>()
    private var payModelItemArray = ArrayList<PayModelItem>()

    override suspend fun saveOperationsToCache(operationsModelItem: List<OperationsModelDB>) {
        operationsModelArray.clear()
        operationsModelArray = ArrayList(operationsModelItem)
    }

    override suspend fun getOperationsFromCache(): List<OperationsModelDB> {
        return operationsModelArray
    }

    override suspend fun savePaysToCache(payModelDBItem: List<PayModelItem>) {
        payModelItemArray.clear()
        payModelItemArray = ArrayList(payModelDBItem)
    }

    override suspend fun getPaysFromCache(): List<PayModelItem> {
        return payModelItemArray
    }
}