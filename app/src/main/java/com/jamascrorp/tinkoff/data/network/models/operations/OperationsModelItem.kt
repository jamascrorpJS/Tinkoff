package com.jamascrorp.tinkoff.data.network.models.operations

data class OperationsModelItem(
    val category: String,
    val id: String,
    val name: String,
    val price: String,
    val type: String,
    val address: String,
    val color: String,
    val time: String
)