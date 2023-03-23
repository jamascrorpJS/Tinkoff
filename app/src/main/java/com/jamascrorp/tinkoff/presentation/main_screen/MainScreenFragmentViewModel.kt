package com.jamascrorp.tinkoff.presentation.main_screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jamascrorp.tinkoff.R
import com.jamascrorp.tinkoff.domain.entity.AdvertisingModel
import com.jamascrorp.tinkoff.domain.entity.OperationsModel
import com.jamascrorp.tinkoff.domain.use_cases.GetOperationsUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainScreenFragmentViewModel @Inject constructor(
    private val getOperationsUseCase: GetOperationsUseCase,
) : ViewModel() {

    private var _operationsLiveData = MutableLiveData<List<OperationsModel>>()
    val operationsLiveData = _operationsLiveData

    fun getOperations() {
        viewModelScope.launch {
            _operationsLiveData.value = getOperationsUseCase.invoke()
        }
    }

    val model = listOf(
        AdvertisingModel(
            R.drawable.beauty_ic_cashback,
            "Загляните в истории"
        ),
        AdvertisingModel(
            R.drawable.beauty_ic_cashback,
            "Загляните в истории"
        ),
        AdvertisingModel(
            R.drawable.beauty_ic_cashback,
            "Загляните в истории"
        ),
        AdvertisingModel(
            R.drawable.beauty_ic_cashback,
            "Загляните в истории"
        ),
        AdvertisingModel(
            R.drawable.beauty_ic_cashback,
            "Загляните в истории"
        ),
        AdvertisingModel(
            R.drawable.beauty_ic_cashback,
            "Загляните в истории"
        ),
        AdvertisingModel(
            R.drawable.beauty_ic_cashback,
            "Загляните в истории"
        ),
    )
}