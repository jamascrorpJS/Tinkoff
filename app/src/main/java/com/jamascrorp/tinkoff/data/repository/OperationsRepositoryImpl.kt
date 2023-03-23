package com.jamascrorp.tinkoff.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jamascrorp.tinkoff.R
import com.jamascrorp.tinkoff.data.database.models.operations.OperationsModelDB
import com.jamascrorp.tinkoff.domain.entity.OperationsModel
import com.jamascrorp.tinkoff.domain.repository.CacheDataSource
import com.jamascrorp.tinkoff.domain.repository.LocalDataSourceRepository
import com.jamascrorp.tinkoff.domain.repository.NetworkDataSource
import com.jamascrorp.tinkoff.domain.repository.OperationsRepository
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class OperationsRepositoryImpl @Inject constructor(
    private val localDataSourceRepository: LocalDataSourceRepository,
    private val networkDataSource: NetworkDataSource,
    private val cacheDataSource: CacheDataSource,
) : OperationsRepository {

    var operationsList: List<OperationsModel>? = null

    private val liveData = MutableLiveData<String>()
    val ld = liveData

    override fun getException(): LiveData<String> {
        return ld
    }

    override suspend fun getOperations(): List<OperationsModel>? {
        return getOperationsFromCache()
    }

    override suspend fun updateOperations(): List<OperationsModel>? {
        val newListOperations = getOperationsFromApi()
        localDataSourceRepository.delOperationsFromDB()
        newListOperations?.let {
            localDataSourceRepository.saveOperationsToDB(it.map {
                OperationsModelDB(
                    null,
                    R.mipmap.ic_launcher,
                    it.costName,
                    it.costType,
                    it.price,
                    it.priceType,
                    it.time,
                    it.color,
                    it.address
                )
            })
        }
        newListOperations?.let {
            cacheDataSource.saveOperationsToCache(it.map {
                OperationsModelDB(
                    null,
                    R.mipmap.ic_launcher,
                    it.costName,
                    it.costType,
                    it.price,
                    it.priceType,
                    it.time,
                    it.color,
                    it.address
                )
            })
        }
        return newListOperations
    }

    suspend fun getOperationsFromApi(): List<OperationsModel>? {
        try {
            val response = networkDataSource.getOperations()
            val body = response.body()
            Log.d("TAG", "getOperationsFromApi: $body")
            if (body != null) {
                operationsList = body.map {
                    OperationsModel(
                        R.mipmap.ic_launcher,
                        it.name,
                        it.category,
                        it.price,
                        it.type,
                        it.time,
                        it.color,
                        it.address
                    )
                }
            }
        } catch (e: Exception) {
            when (e) {
                is SocketTimeoutException -> {
                    liveData.value = "Истекло время ожидания соединения"
                }
                is UnknownHostException -> {
                    liveData.value = "Интернет недоступен"
                }
            }
        }
        return operationsList
    }

    suspend fun getOperationsFromDB(): List<OperationsModel>? {
        try {
            operationsList = localDataSourceRepository.getOperationsFromDB().map {
                OperationsModel(
                    R.mipmap.ic_launcher,
                    it.costName,
                    it.costType,
                    it.price,
                    it.priceType,
                    it.time,
                    it.color,
                    it.address
                )
            }
        } catch (exception: java.lang.Exception) {
            Log.i("MyTag", exception.message.toString())
        }
        if (!operationsList.isNullOrEmpty()) {
            return operationsList
        } else {
            operationsList = getOperationsFromApi()
            operationsList?.let {
                localDataSourceRepository.saveOperationsToDB(it.map {
                    OperationsModelDB(
                        null,
                        R.mipmap.ic_launcher,
                        it.costName,
                        it.costType,
                        it.price,
                        it.priceType,
                        it.time,
                        it.color,
                        it.address
                    )
                })
            }
        }

        return operationsList
    }

    suspend fun getOperationsFromCache(): List<OperationsModel>? {
        try {
            operationsList = cacheDataSource.getOperationsFromCache().map {
                OperationsModel(
                    R.mipmap.ic_launcher,
                    it.costName,
                    it.costType,
                    it.price,
                    it.priceType,
                    it.time,
                    it.color,
                    it.address
                )
            }
        } catch (exception: java.lang.Exception) {
            Log.i("MyTag", exception.message.toString())
        }
        Log.d("TAG", "getOperationsFromCache: ${!operationsList.isNullOrEmpty()}")
        if (!operationsList.isNullOrEmpty()) {
            return operationsList
        } else {
            operationsList = getOperationsFromDB()
            operationsList?.let {
                cacheDataSource.saveOperationsToCache(
                    it.map {
                        OperationsModelDB(
                            null,
                            R.mipmap.ic_launcher,
                            it.costName,
                            it.costType,
                            it.price,
                            it.priceType,
                            it.time,
                            it.color,
                            it.address
                        )
                    }
                )
            }
        }
        return operationsList
    }
}