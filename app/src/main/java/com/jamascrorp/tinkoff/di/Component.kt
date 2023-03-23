package com.jamascrorp.tinkoff.di

import com.jamascrorp.tinkoff.di.modules.ContextModule
import com.jamascrorp.tinkoff.di.modules.DatabaseModule
import com.jamascrorp.tinkoff.di.modules.DomainsModule
import com.jamascrorp.tinkoff.di.modules.RetrofitModule
import com.jamascrorp.tinkoff.presentation.final_pay_screen.FinalPayFragment
import com.jamascrorp.tinkoff.presentation.main_screen.MainScreenFragment
import com.jamascrorp.tinkoff.presentation.operation_screen.OperationScreenFragment
import com.jamascrorp.tinkoff.presentation.pay_screen.PayScreenFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ContextModule::class, DomainsModule::class, RetrofitModule::class, DatabaseModule::class])
interface Component {

    fun inject(fragment: PayScreenFragment)
    fun inject(fragment: OperationScreenFragment)
    fun inject(fragment: FinalPayFragment)
    fun inject(fragment: MainScreenFragment)

}