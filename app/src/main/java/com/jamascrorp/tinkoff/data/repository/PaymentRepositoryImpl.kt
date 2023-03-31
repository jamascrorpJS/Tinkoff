package com.jamascrorp.tinkoff.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jamascrorp.tinkoff.data.network.Network
import com.jamascrorp.tinkoff.data.network.models.operations.OperationsModelItem
import com.jamascrorp.tinkoff.domain.repository.NetworkDataSource
import com.jamascrorp.tinkoff.domain.repository.PaymentRepository
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

class PaymentRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource,
) : PaymentRepository {

    private val liveDataPays = MutableLiveData<Network<ResponseBody>>()
    private val postPayLiveData: LiveData<Network<ResponseBody>>
        get() = liveDataPays

    private val livedata = MutableLiveData<String>()
    val liveData = livedata

    override fun getPayments(): LiveData<Network<ResponseBody>> {
        return postPayLiveData
    }

    override fun getExcept(): LiveData<String> {
        return liveData
    }

    override suspend fun postPays(operationsModelItem: OperationsModelItem) {
        try {
            liveDataPays.postValue(Network.Loading())
            val response = networkDataSource.postOperations(operationsModelItem)
            handleResponse(response, liveDataPays)
        } catch (e: Exception) {
            liveDataPays.postValue(Network.Exception(e.fillInStackTrace()))
            livedata.postValue("Нет доступа к серверу")
        }
    }

    private fun <T> handleResponse(response: Response<T>, liveData: MutableLiveData<Network<T>>) {
        if (response.isSuccessful && response.body() != null) {
            liveData.postValue(Network.Success(response.body()!!))
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            liveData.postValue(Network.Error(errorObj.getString("message")))
        } else {
            liveData.postValue(Network.Error("Something Went Wrong"))
        }
    }
}