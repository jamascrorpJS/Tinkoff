package com.jamascrorp.tinkoff.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jamascrorp.tinkoff.R
import com.jamascrorp.tinkoff.data.network.models.pay.PayModelItem
import com.jamascrorp.tinkoff.domain.entity.PayModel
import com.jamascrorp.tinkoff.domain.repository.CacheDataSource
import com.jamascrorp.tinkoff.domain.repository.LocalDataSourceRepository
import com.jamascrorp.tinkoff.domain.repository.NetworkDataSource
import com.jamascrorp.tinkoff.domain.repository.PaysRepository
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class PaysRepositoryImpl @Inject constructor(
    private val localDataSourceRepository: LocalDataSourceRepository,
    private val networkDataSource: NetworkDataSource,
    private val cacheDataSource: CacheDataSource,
) : PaysRepository {

    var payModelList: List<PayModelItem>? = null

    private val liveData = MutableLiveData<String>()
    val ld = liveData

    override fun getException(): LiveData<String> {
        return ld
    }

    override suspend fun getPays(): List<PayModel>? {
        return getPaysFromCache()?.map {
            PayModel(
                R.mipmap.ic_launcher,
                it.category,
                it.id,
                it.subs
            )
        }
    }

    suspend fun getPaysFromAPI(): List<PayModelItem>? {
        try {
            val response = networkDataSource.getPays()
            val body = response.body()
            if (body != null) {
                payModelList = body.map {
                    PayModelItem(
                        it.category,
                        it.icon,
                        it.id,
                        it.subs
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
        return payModelList
    }

    suspend fun getPaysFromDB(): List<PayModelItem>? {
        try {
            payModelList = localDataSourceRepository.getPaysFromDB().map {
                PayModelItem(
                    it.category,
                    it.icon.toString(),
                    it.id.toString(),
                    it.subs.map {
                        com.jamascrorp.tinkoff.data.network.models.pay.Subs(
                            it.address,
                            it.color,
                            it.icon,
                            it.name
                        )
                    }
                )
            }
        } catch (exception: Exception) {
            Log.i("MyTag", exception.message.toString())
        }
        if (!payModelList.isNullOrEmpty()) {
            return payModelList
        } else {
            payModelList = getPaysFromAPI()
            payModelList?.let {
                localDataSourceRepository.savePaysToDB(it.map {
                    com.jamascrorp.tinkoff.data.database.models.pay.PayModelItem(
                        it.id.toInt(),
                        it.category,
                        R.mipmap.ic_launcher,
                        it.subs.map {
                            com.jamascrorp.tinkoff.data.database.models.pay.Subs(
                                null,
                                it.address,
                                it.color,
                                it.icon,
                                it.name
                            )
                        }
                    )
                })
            }
        }
        return payModelList
    }

    suspend fun getPaysFromCache(): List<PayModelItem>? {
        try {
            payModelList = cacheDataSource.getPaysFromCache().map {
                PayModelItem(
                    it.category,
                    it.icon.toString(),
                    it.id.toString(),
                    it.subs.map {
                        com.jamascrorp.tinkoff.data.network.models.pay.Subs(
                            it.address,
                            it.color,
                            it.icon,
                            it.name
                        )
                    }
                )
            }
        } catch (exception: Exception) {
            Log.i("MyTag", exception.message.toString())
        }
        Log.d("TAG", "getPaysFromCache: ${!payModelList.isNullOrEmpty()}")
        if (!payModelList.isNullOrEmpty()) {
            return payModelList
        } else {
            payModelList = getPaysFromDB()
            payModelList?.let {
                cacheDataSource.savePaysToCache(it.map {
                    com.jamascrorp.tinkoff.data.database.models.pay.PayModelItem(
                        it.id.toInt(),
                        it.category,
                        R.mipmap.ic_launcher,
                        it.subs.map {
                            com.jamascrorp.tinkoff.data.database.models.pay.Subs(
                                null,
                                it.address,
                                it.color,
                                it.icon,
                                it.name
                            )
                        }
                    )
                })
            }
        }
        return payModelList
    }
}