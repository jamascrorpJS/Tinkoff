package com.jamascrorp.tinkoff.presentation.pay_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jamascrorp.tinkoff.data.database.models.bookmarks.BookmarksModelDB
import com.jamascrorp.tinkoff.domain.entity.PayModel
import com.jamascrorp.tinkoff.domain.use_cases.GetFromDBUseCase
import com.jamascrorp.tinkoff.domain.use_cases.GetPaysUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class PayScreenViewModel @Inject constructor(
    private val getPaysUseCase: GetPaysUseCase,
    private val getFromDBUseCase: GetFromDBUseCase,
) : ViewModel() {

    private val liveData = MutableLiveData<List<PayModel>?>()
    val ld: LiveData<List<PayModel>?> = liveData

    val liveData1: LiveData<String>
        get() = getPaysUseCase.getException()

    private val livedataDB = MutableLiveData<List<BookmarksModelDB>>()
    val ldDB = livedataDB

    fun getBookmarks() {
        viewModelScope.launch {
            livedataDB.value = getFromDBUseCase.getBookmarks()
        }
    }

    fun getPay() {
        viewModelScope.launch {
            liveData.value = getPaysUseCase.invoke()
        }
    }
}