package com.jamascrorp.tinkoff.di.modules

import com.jamascrorp.tinkoff.data.repository.*
import com.jamascrorp.tinkoff.domain.repository.*
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface DomainsModule {

    @Singleton
    @Binds
    fun bindLocalDataSource(impl: LocalDataSourceRepositoryImpl): LocalDataSourceRepository

    @Singleton
    @Binds
    fun networkDataSource(impl: NetworkDataSourceImpl): NetworkDataSource

    @Singleton
    @Binds
    fun cacheDataSource(Impl: CacheDataSourceRepository): CacheDataSource

    @Singleton
    @Binds
    fun bindOperationsRepository(impl: OperationsRepositoryImpl): OperationsRepository

    @Singleton
    @Binds
    fun bindPayRepository(impl: PaysRepositoryImpl): PaysRepository

    @Singleton
    @Binds
    fun bindPaymentRepository(impl: PaymentRepositoryImpl): PaymentRepository

}