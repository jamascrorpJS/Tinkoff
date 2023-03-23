package com.jamascrorp.tinkoff.presentation.operation_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jamascrorp.tinkoff.domain.entity.OperationsModel
import com.jamascrorp.tinkoff.domain.use_cases.GetOperationsUseCase
import com.jamascrorp.tinkoff.domain.use_cases.UpdateOperationsUseCase
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class OperationScreenViewModel @Inject constructor(
    private val getOperationsUseCase: GetOperationsUseCase,
    private val updateOperationsUseCase: UpdateOperationsUseCase,
) : ViewModel() {

    private val liveData = MutableLiveData<List<OperationsModel>>()
    val ld: LiveData<List<OperationsModel>> = liveData

    val liveData1: LiveData<String>
        get() = getOperationsUseCase.getException()

    fun getOperations() {
        viewModelScope.launch {
            liveData.value = getOperationsUseCase.invoke()
        }
    }

    fun updateOperations() {
        viewModelScope.launch {
            liveData.value = updateOperationsUseCase.invoke()
        }
    }

    fun getMonth():String {
        val myDate: Date = Calendar.getInstance().time
        val month = myDate.month
        return when (month) {
            0 -> "Январь"
            1 -> "Февраль"
            2 -> "Март"
            3 -> "Апрель"
            4 -> "Май"
            5 -> "Июнь"
            6 -> "Июль"
            7 -> "Август"
            8 -> "Сентябрь"
            9 -> "Октябрь"
            10 -> "Ноябрь"
            else -> "Декабрь"
        }

    }
}