package com.jamascrorp.tinkoff.domain.repository

import androidx.lifecycle.LiveData
import com.jamascrorp.tinkoff.domain.entity.OperationsModel

interface OperationsRepository {

    suspend fun getOperations(): List<OperationsModel>?
    suspend fun updateOperations() : List<OperationsModel>?
    fun getException() : LiveData<String>
}