package com.jamascrorp.tinkoff.di

import android.app.Application
import com.jamascrorp.tinkoff.di.modules.ContextModule
import com.jamascrorp.tinkoff.di.modules.RetrofitModule
import com.jamascrorp.tinkoff.presentation.final_pay_screen.FinalPayFragment
import com.jamascrorp.tinkoff.presentation.main_screen.MainScreenFragment
import com.jamascrorp.tinkoff.presentation.operation_screen.OperationScreenFragment
import com.jamascrorp.tinkoff.presentation.pay_screen.PayScreenFragment

class Inject : Application(), Component {

    private lateinit var component: Component
    override fun onCreate() {
        super.onCreate()
        component = DaggerComponent.builder()
            .contextModule(ContextModule(applicationContext))
            .retrofitModule(RetrofitModule("https://6416fd23205bdf0a1d7d9a46.mockapi.io/api/v1/"))
            .build()
    }

    override fun inject(fragment: PayScreenFragment) {
        component.inject(fragment)
    }

    override fun inject(fragment: OperationScreenFragment) {
        component.inject(fragment)
    }

    override fun inject(fragment: FinalPayFragment) {
        component.inject(fragment)
    }

    override fun inject(fragment: MainScreenFragment) {
        component.inject(fragment)
    }

}