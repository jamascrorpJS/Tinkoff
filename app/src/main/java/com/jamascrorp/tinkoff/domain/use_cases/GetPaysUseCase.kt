package com.jamascrorp.tinkoff.domain.use_cases

import androidx.lifecycle.LiveData
import com.jamascrorp.tinkoff.domain.entity.PayModel
import com.jamascrorp.tinkoff.domain.repository.PaysRepository
import javax.inject.Inject

class GetPaysUseCase @Inject constructor(private val repository: PaysRepository) {

    suspend operator fun invoke(): List<PayModel>? {
        return repository.getPays()
    }

    fun getException(): LiveData<String> {
        return repository.getException()
    }
}