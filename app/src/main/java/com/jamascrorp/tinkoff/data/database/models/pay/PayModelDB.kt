package com.jamascrorp.tinkoff.data.database.models.pay

import com.google.gson.annotations.SerializedName


data class PayModelDB(
    @SerializedName("pay_results")
    val payModelItem: ArrayList<PayModelItem>,
)