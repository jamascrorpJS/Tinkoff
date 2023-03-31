package com.jamascrorp.tinkoff.presentation.final_pay_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jamascrorp.tinkoff.data.database.models.bookmarks.BookmarksModelDB
import com.jamascrorp.tinkoff.data.network.Network
import com.jamascrorp.tinkoff.data.network.models.operations.OperationsModelItem
import com.jamascrorp.tinkoff.domain.use_cases.GetPaymentUseCase
import com.jamascrorp.tinkoff.domain.use_cases.GetSaveToDBUseCase
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import javax.inject.Inject

class FinalPayFragmentViewModel @Inject constructor(
    private val getPaymentUseCase: GetPaymentUseCase,
    private val getSaveToDBUseCase: GetSaveToDBUseCase
) : ViewModel() {

    val liveData: LiveData<Network<ResponseBody>>
        get() = getPaymentUseCase.invoke()

    val liveData1: LiveData<String>
        get() = getPaymentUseCase.getExcept()

    fun postPay(operationsModelItem: OperationsModelItem) {
        viewModelScope.launch {
            getPaymentUseCase.initResponse(operationsModelItem)
        }
    }

    fun save(bookmarksModelDB: BookmarksModelDB) {
        viewModelScope.launch{
            getSaveToDBUseCase.saveBookmarksToDB(bookmarksModelDB)
        }
    }

}