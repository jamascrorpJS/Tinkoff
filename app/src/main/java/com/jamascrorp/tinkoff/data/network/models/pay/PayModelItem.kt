package com.jamascrorp.tinkoff.data.network.models.pay

data class PayModelItem(
    val category: String,
    val icon: String,
    val id: String,
    val subs: List<Subs>,
)