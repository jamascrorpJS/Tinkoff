package com.jamascrorp.tinkoff.domain.repository

import androidx.lifecycle.LiveData
import com.jamascrorp.tinkoff.domain.entity.PayModel

interface PaysRepository {
    suspend fun getPays(): List<PayModel>?
    fun getException() : LiveData<String>
}